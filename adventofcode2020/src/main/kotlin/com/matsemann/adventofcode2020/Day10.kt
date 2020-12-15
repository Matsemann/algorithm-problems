package com.matsemann.adventofcode2020

import java.math.BigInteger
import kotlin.math.abs


fun day10_1(lines: List<String>,) {
    val numbers = lines.map { it.toInt() }
        .sorted()

    val adaptors = numbers
        .plus(0)
        .plus(numbers.last() + 3)
        .sorted()

    val diffs = adaptors.zipWithNext { a, b -> b - a }

    val counts = diffs.groupBy { it }.mapValues { it.value.size }

    println("result: " + counts[1]!! * counts[3]!!)


}

fun day10_2(lines: List<String>,) {
    val numbers = lines.map { it.toInt() }
        .sorted()

    val adaptors = numbers
        .plus(numbers.last() + 3)
        .sorted()

    val counts = mutableMapOf<Int, Int>()
    counts[0] = 1

    adaptors.forEach {
        val sum = (counts[it - 3] ?: 0) + (counts[it - 2] ?: 0) + (counts[it-1] ?: 0)
        counts[it] = sum
    }
    println()


    /*

    (0) 1, 4, 5, 6, 7,      10, 11, 12, 15, 16, 19 (22)
    1 4 5 6
    1 4 6

    1: 1
    4: 1 = 1
    5: 1 = 1
    6: 1 + 1 = 2
    7: 1 + 1 + 2 = 4

     */
}
