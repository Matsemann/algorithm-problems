package com.matsemann.adventofcode2021

fun day01_1(lines: List<String>) {
    lines.forEach { print(it)}
}


fun day01_2(numbers: List<String>) {

}

fun main() {
    measure {
        day01_1(getFileLines("day01_1.txt"))
    }
}