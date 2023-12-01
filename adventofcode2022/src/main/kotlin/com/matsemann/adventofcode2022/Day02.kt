package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*

fun day02_1(lines: List<String>): Any {
    return lines
        .map {
            when (it) {
                "A X" -> 1 + 3
                "A Y" -> 2 + 6
                "A Z" -> 3 + 0
                "B X" -> 1 + 0
                "B Y" -> 2 + 3
                "B Z" -> 3 + 6
                "C X" -> 1 + 6
                "C Y" -> 2 + 0
                "C Z" -> 3 + 3
                else -> 0
            }
        }.sum()
}

fun day02_2(lines: List<String>): Any {
    return lines
        .map {
            when (it) {
                "A X" -> 3 + 0
                "A Y" -> 1 + 3
                "A Z" -> 2 + 6
                "B X" -> 1 + 0
                "B Y" -> 2 + 3
                "B Z" -> 3 + 6
                "C X" -> 2 + 0
                "C Y" -> 3 + 3
                "C Z" -> 1 + 6
                else -> 0
            }
        }.sum()
}

fun main() {

//    run("1", fileName = "day02_ex.txt", func = ::day02_1)
//    run("2", fileName = "day02_ex.txt", func = ::day02_2)

    run("1", fileName = "day02.txt", func = ::day02_1)
    run("2", fileName = "day02.txt", func = ::day02_2)
}

