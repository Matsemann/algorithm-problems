package com.matsemann.adventofcode2020

import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.test.Test

class Runner {


    fun getFileScanner(fileName: String) =
        Scanner(FileInputStream(fileName))

    fun getFileLines(fileName: String) =
        Files.readAllLines(Path.of(fileName))


    @Test
    fun day01_1Runner() {
        dayX_1(getFileLines("inputs/day1_1.txt"))
    }

    @Test
    fun day01_2Runner() {
        dayX_2(getFileLines("inputs/day1_1.txt"))
    }

    @Test
    fun dayX_1Runner() {
        dayX_1(getFileLines("inputs/day1_1.txt"))
    }

    @Test
    fun dayX_2Runner() {
        dayX_2(getFileLines("inputs/day1_1.txt"))
    }


}