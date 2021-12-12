package com.matsemann.adventofcode2021

import java.awt.Toolkit
import java.awt.datatransfer.StringSelection
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.sign
import kotlin.math.sqrt


fun getFileLines(fileName: String) =
    Files.readAllLines(Path.of("adventofcode2021/inputs/$fileName"))

fun run(runName: String? = null, fileName: String, func: (List<String>) -> Any) {
    val lines = getFileLines(fileName)
    val result = measure { func(lines) }

    println("Result for ${runName ?: fileName}:\t${result}")
    try {
        Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(result.toString()), null)
        println("Copied to clipboard!\n")
    } catch (_: Exception) {

    }
}

fun <T> measure(func: () -> T): T {
    val start = System.currentTimeMillis()

    return func().also {
        val time = System.currentTimeMillis() - start
        println("Done. Took ${time}ms to run")
    }
}


enum class Direction(var x: Int, var y: Int) {
    RIGHT(1, 0),
    DOWN(0, -1),
    LEFT(-1, 0),
    UP(0, 1);

    fun turnCw() =
        when (this) {
            RIGHT -> DOWN
            DOWN -> LEFT
            LEFT -> UP
            UP -> RIGHT
        }

    fun turnCcw() =
        when (this) {
            RIGHT -> UP
            DOWN -> RIGHT
            LEFT -> DOWN
            UP -> LEFT
        }

    fun flip() =
        when (this) {
            RIGHT -> LEFT
            LEFT -> RIGHT
            UP -> DOWN
            DOWN -> UP
        }

    companion object {
        fun fromNSEW(ch: Char) {
            when (ch) {
                'N' -> UP
                'S' -> DOWN
                'E' -> RIGHT
                'W' -> LEFT
            }
        }

        fun fromUDLR(ch: Char) {
            when (ch) {
                'U' -> UP
                'D' -> DOWN
                'R' -> RIGHT
                'L' -> LEFT
            }
        }

        fun setYDown() {
            DOWN.y = 1
            UP.y = -1
        }
    }
}

data class Vec2d(val x: Double, val y: Double) {
    operator fun plus(other: Vec2d) = Vec2d(x + other.x, y + other.y)
    operator fun plus(other: IntVec) = Vec2d(x + other.x, y + other.y)
    operator fun plus(dir: Direction) = Vec2d(x + dir.x, y + dir.y)

    operator fun minus(other: Vec2d) = Vec2d(x - other.x, y - other.y)
    operator fun unaryMinus() = Vec2d(-x, -y)

    operator fun times(factor: Double) = Vec2d(x * factor, y * factor)

    fun manhattan(other: Vec2d) = abs(x - other.x) + abs(y - other.y)
    fun dst(other: Vec2d) = sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y))
    fun len() = dst(zero)
    fun norm() = times(1 / len())
    fun dot(other: Vec2d) = x * other.x + y * other.y
    fun cross(other: Vec2d) = x * y - y * x

    fun isPerpendicular(other: Vec2d) = abs(dot(other)) < 0.000001
    fun hasSameDirection(other: Vec2d) = dot(other) > 0.0
    fun hasOppositeDirection(other: Vec2d) = dot(other) < 0.0

    companion object {
        val zero = Vec2d(0.0, 0.0)
    }
}


data class IntVec(val x: Int, val y: Int) {
    operator fun plus(other: IntVec) = IntVec(x + other.x, y + other.y)
    operator fun plus(dir: Direction) = IntVec(x + dir.x, y + dir.y)

    operator fun minus(other: IntVec) = IntVec(x - other.x, y - other.y)
    operator fun unaryMinus() = IntVec(-x, -y)

    operator fun times(factor: Int) = IntVec(x * factor, y * factor)

    fun manhattan(other: IntVec) = abs(x - other.x) + abs(y - other.y)
    fun manhattan() = manhattan(zero)
    fun chebyshev(other: IntVec) = max(abs(x - other.x), abs(y - other.y))
    fun chebyshev() = chebyshev(zero)

    fun asDir() = IntVec(x.sign, y.sign)

    // Note, it is inclusive, so for indexing reduce with 1
    fun withinBounds(bounds: IntVec) = withinBounds(0, bounds.x, 0, bounds.y)
    fun withinBounds(minX: Int, maxX: Int, minY: Int, maxY: Int) =
        x in minX..maxX && y in minY..maxY

    fun neighbors() = Direction.values().map { this + it }
    fun neighbors(bounds: IntVec) = Direction.values().map { this + it }
        .filter { it.withinBounds(bounds) }

    fun neighbors9() = (-1..1)
        .flatMap { x ->
            (-1..1)
                .map { y -> IntVec(x, y) }
        }
        .filter { it.x != 0 || it.y != 0 }
        .map { this + it }

    companion object {
        val zero = IntVec(0, 0)

        fun fromStr(str: String, delim: String = ","): IntVec {
            val split = str.split(delim)
            return IntVec(split[0].toInt(), split[1].toInt())
        }

        fun String.toIntVec(delim: String = ",") = fromStr(this, delim)

    }
}

operator fun <E> List<List<E>>.get(intVec: IntVec) = this[intVec.y][intVec.x]
operator fun <E> List<MutableList<E>>.set(intVec: IntVec, value: E) {
    this[intVec.y][intVec.x] = value
}

fun <E> permutations(list: List<E>, length: Int? = null): Sequence<List<E>> = sequence {
    val n = list.size
    val r = length ?: list.size

    val indices = list.indices.toMutableList()
    val cycles = (n downTo (n - r)).toMutableList()
    yield(indices.take(r).map { list[it] })

    while (true) {
        var broke = false
        for (i in (r - 1) downTo 0) {
            cycles[i]--
            if (cycles[i] == 0) {
                val end = indices[i]
                for (j in i until indices.size - 1) {
                    indices[j] = indices[j + 1]
                }
                indices[indices.size - 1] = end
                cycles[i] = n - i
            } else {
                val j = cycles[i]
                val tmp = indices[i]
                indices[i] = indices[-j + indices.size]
                indices[-j + indices.size] = tmp
                yield(indices.take(r).map { list[it] })
                broke = true
                break
            }
        }
        if (!broke) {
            break
        }
    }
}

fun <E, F> cartesian(list1: List<E>, list2: List<F>): Sequence<Pair<E, F>> =
    cartesian(listOf(list1, list2)).map { it[0] as E to it[1] as F }

fun <E, F, G> cartesian(list1: List<E>, list2: List<F>, list3: List<G>): Sequence<Triple<E, F, G>> =
    cartesian(listOf(list1, list2, list3)).map { Triple(it[0] as E, it[1] as F, it[2] as G) }

fun <E> cartesian(lists: List<List<E>>): Sequence<List<E>> {
    return sequence {
        val counters = Array(lists.size) { 0 }
        val length = lists.fold(1) { acc, list -> acc * list.size }

        for (i in 0 until length) {
            val result = lists.mapIndexed { index, list ->
                list[counters[index]]
            }
            yield(result)
            for (pointer in lists.size - 1 downTo 0) {
                counters[pointer]++
                if (counters[pointer] == lists[pointer].size) {
                    counters[pointer] = 0
                } else {
                    break
                }
            }
        }
    }
}