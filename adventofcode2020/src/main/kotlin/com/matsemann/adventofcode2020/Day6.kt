package com.matsemann.adventofcode2020

fun day6_1(lines: List<String>) {

    val groups = lines.joinToString("\n").split("\n\n")
    val yeses = groups.map {
        it.replace("\n", "").toSet().size
    }
    val sum = yeses.sum()

    println("sum: $sum")
}

fun day6_2(lines: List<String>) {

    val groups = lines.joinToString("\n").split("\n\n")
    val allYeses = groups.map {group ->
        val answers = group.split("\n").map { it.toSet() }
        answers.reduce {acc, set -> acc.intersect(set) }.size
    }

    val sum = allYeses.sum()

    println("sum: $sum")
}
