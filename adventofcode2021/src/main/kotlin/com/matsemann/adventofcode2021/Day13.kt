package com.matsemann.adventofcode2021

import com.matsemann.adventofcode2021.IntVec.Companion.bounds
import com.matsemann.adventofcode2021.IntVec.Companion.showAsGrid
import com.matsemann.adventofcode2021.IntVec.Companion.toIntVec


fun fold(lines: List<String>, numFolds: Int = 0): List<IntVec> {
    val initialPoints = lines.filter { it.contains(",") }.map { it.toIntVec() }

    val initialInstructions = lines.filter { it.contains("fold along") }.map { it.split(" ")[2] }
        .map {
            val a = it.split("=")
            a[0] to a[1].toInt()
        }

    val folds = if (numFolds > 0) initialInstructions.take(numFolds) else initialInstructions

    return folds.fold(initialPoints) { points, fold ->
        points.map { point ->
            if (fold.first == "y" && point.y > fold.second) {
                IntVec(point.x, fold.second - (point.y - fold.second))
            } else if (fold.first == "x" && point.x > fold.second) {
                IntVec(fold.second - (point.x - fold.second), point.y)
            } else {
                point
            }
        }.distinct()
    }
}

fun day13_1(lines: List<String>) = fold(lines, 1).size

fun day13_2(lines: List<String>) = "\n" + fold(lines).showAsGrid()

fun main() {
//    run("1", fileName = "day13_ex.txt", func = ::day13_1)
//    run("1", fileName = "day13_1.txt", func = ::day13_1)
//    run("2", fileName = "day13_ex.txt", func = ::day13_2)
    run("2", fileName = "day13_ex3.txt", func = ::day13_2)
//        run("2", fileName = "day13_gen3.txt") {
//            generator(it, 30)
//        }
}


fun generator(lines: List<String>, numFolds: Int) {
    val result = lines.mapIndexed { y, line ->
        line.mapIndexedNotNull { x, c ->
            if (c == fullBlock) IntVec(x, y) else null
        }
    }.flatten()
    val (_, maxX, _, maxY) = result.bounds()
    println("initial points: ${result.size}")

    var points = result
    val folds = mutableListOf<Pair<String,Int>>()

    var width = maxX + 1
    var height = maxY + 1
    for (i in 0 until numFolds) {
        val axis = listOf("x", "y").random()
        val line = if (axis == "x") width else height
        folds += axis to line

        if (axis == "x") {
            width = 2 * width + 1
        } else {
            height = 2 * height + 1
        }

        points = points.flatMap { point ->
            val mirrored = if (axis == "x") point.mirrorX(line) else point.mirrorY(line)
            when(listOf(1, 1, 1, 1, 3, 3, 3).random()) {
                1 -> listOf(mirrored)
                2 -> listOf(point, mirrored)
                else -> listOf(point)
            }
        }
        println("points now: ${points.size}")
    }
    println()
    points.forEach { println("${it.x},${it.y}") }
    println()
    folds.reversed().forEach { println("fold along ${it.first}=${it.second}") }
}

/*
OUTPUT
======

Done. Took 1ms to run
Result for 1:	775
Copied to clipboard!

###..####.#..#.###..#..#.###..#..#.###.............
#..#.#....#..#.#..#.#..#.#..#.#.#..#..#............
#..#.###..#..#.#..#.#..#.#..#.##...#..#............
###..#....#..#.###..#..#.###..#.#..###.............
#.#..#....#..#.#....#..#.#....#.#..#.#.............
#..#.####..##..#.....##..#....#..#.#..#............
...................................................
...................................................
...................................................
...................................................
...................................................
Done. Took 3ms to run
Result for 2:
Copied to clipboard!

 */