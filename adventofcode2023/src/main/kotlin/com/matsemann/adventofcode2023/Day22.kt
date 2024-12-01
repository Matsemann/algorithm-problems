package com.matsemann.adventofcode2023

import com.matsemann.adventofcode2023.utils.*

data class Vec3D(var x: Int, var y: Int, var z: Int)

data class Cube(val name: Int, val p1: Vec3D, val p2: Vec3D) {
    fun overlaps(other: Cube): Boolean {
        return p1.x <= other.p2.x && p2.x >= other.p1.x &&
                p1.y <= other.p2.y && p2.y >= other.p1.y &&
                p1.z <= other.p2.z && p2.z >= other.p1.z
    }
}

fun day22_1(lines: List<String>): Any {
    val cubes = lines.mapIndexed { i, line ->
        line.split("~").let {
            val (x1, y1, z1) = it[0].allInts()
            val (x2, y2, z2) = it[1].allInts()
            Cube(i, Vec3D(x1, y1, z1), Vec3D(x2, y2, z2))
        }
    }.sortedBy { it.p1.z }

    val supportedBy = mutableMapOf<Cube, List<Cube>>()

    cubes.forEachIndexed { index, cube ->
        val lowerCubes = cubes.take(index)
        var steps = 0
        while (true) {
            val trySteps = steps + 1

            if (cube.p1.z - trySteps == 0) {
                break
            }
            val newCube = Cube(
                0,
                cube.p1.copy(z = cube.p1.z - trySteps),
                cube.p2.copy(z = cube.p2.z - trySteps)
            )
            val overlaps = lowerCubes.filter { newCube.overlaps(it) }
            if (overlaps.isNotEmpty()) {
                supportedBy[cube] = overlaps
                break
            }
            steps = trySteps
        }
        cube.p1.z -= steps
        cube.p2.z -= steps
    }

    return cubes.size - supportedBy.values.filter { it.size == 1 }.flatten().toSet().size
}


fun day22_2(lines: List<String>): Any {
    val cubes = lines.mapIndexed { i, line ->
        line.split("~").let {
            val (x1, y1, z1) = it[0].allInts()
            val (x2, y2, z2) = it[1].allInts()
            Cube(i, Vec3D(x1, y1, z1), Vec3D(x2, y2, z2))
        }
    }.sortedBy { it.p1.z }

    val supportedBy = mutableMapOf<Cube, List<Cube>>()

    cubes.forEachIndexed { index, cube ->
        val lowerCubes = cubes.take(index)
        var steps = 0
        while (true) {
            val trySteps = steps + 1

            if (cube.p1.z - trySteps == 0) {
                break
            }
            val newCube = Cube(
                0,
                cube.p1.copy(z = cube.p1.z - trySteps),
                cube.p2.copy(z = cube.p2.z - trySteps)
            )
            val overlaps = lowerCubes.filter { newCube.overlaps(it) }
            if (overlaps.isNotEmpty()) {
                supportedBy[cube] = overlaps
                break
            }
            steps = trySteps
        }
        cube.p1.z -= steps
        cube.p2.z -= steps
    }

    return cubes.map { cube ->

        val cubesToRemove = mutableListOf(cube)
        var supportedCubes = supportedBy.toMap()
        val removedCubes = mutableSetOf<Cube>()

        while (cubesToRemove.isNotEmpty()) {
            val cubeToRemove = cubesToRemove.removeFirst()

            if (cubeToRemove in removedCubes) { continue }
            removedCubes += cubeToRemove

            supportedCubes = supportedCubes.mapValues { (_, supports) ->
                supports.filter { it.name != cubeToRemove.name }
            }
            val toCheck = supportedCubes.filter { (c, supports) ->
                c !in removedCubes && supports.isEmpty()
            }.keys
            cubesToRemove.addAll(toCheck)
        }
        removedCubes.size-1

    }.sum()

}

fun main() {
//    val a = Cube(Vec3D(0, 0, 0), Vec3D(5, 5, 5))
//    val b = Cube(Vec3D(6, 6, 4), Vec3D(7, 7, 10))
//    println(a.overlaps(b))

//    run("1", fileName = "day22_ex.txt", func = ::day22_1)
    run("2", fileName = "day22_ex.txt", func = ::day22_2)

    // 413
//    run("1", fileName = "day22.txt", func = ::day22_1)
    run("2", fileName = "day22.txt", func = ::day22_2)
}
