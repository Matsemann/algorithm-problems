package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*

fun day01_1(lines: List<String>): Any {
    return lines
        .splitBy { it == "" }
        .map { it.ints().sum() }
        .max()
}


fun day01_2(lines: List<String>): Any {
    return lines
        .splitBy { it == "" }
        .map { it.ints().sum() }
        .sortedDescending()
        .take(3)
        .sum()
}

fun main() {

//    run("1", fileName = "day01_ex.txt", func = ::day01_1)
//    run("2", fileName = "day01_ex.txt", func = ::day01_2)


    run("1", fileName = "day01.txt", func = ::day01_1)
    run("2", fileName = "day01.txt", func = ::day01_2)
}
