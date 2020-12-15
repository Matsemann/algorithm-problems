package com.matsemann.adventofcode2020

import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import kotlin.test.Test

class Runner {


    @Test
    fun day1_1Runner() {
        val numbers = getFileLines("inputs/day1_1.txt").map { it.toInt() }
//        val numbers = getFileLines("inputs/day1_1example.txt").map { it.toInt() }
        day1_1(numbers)
    }

    @Test
    fun day1_2Runner() {
        val numbers = getFileLines("inputs/day1_1.txt").map { it.toInt() }
//        val numbers = getFileLines("inputs/day1_1example.txt").map { it.toInt() }
        day1_2(numbers)
    }


    @Test
    fun day2_1Runner() {
        val passwords = getFileLines("inputs/day02_1.txt").toList()
        day2_1(passwords)
    }

    @Test
    fun day2_2Runner() {
        val passwords = getFileLines("inputs/day02_1.txt").toList()
        day2_2(passwords)
    }

    @Test
    fun day3_1Runner() {
        val mapLines = getFileLines("inputs/day03_example.txt").toList()
        day3_1(mapLines)
    }
    @Test
    fun day3_2Runner() {
        val mapLines = getFileLines("inputs/day03_1.txt").toList()
        day3_2(mapLines)
    }


    @Test
    fun day4_1Runner() {
        val passports = getFileLines("inputs/day04_1.txt").toList()
        day4_1(passports)
    }



    @Test
    fun day4_2Runner() {
        val passports = getFileLines("inputs/day04_1.txt").toList()
        day4_2(passports)
    }



    @Test
    fun day5_1Runner() {
        val pass = getFileLines("inputs/day05_1.txt").toList()
        day5_1(pass)
    }
    @Test
    fun day5_2Runner() {
        val pass = getFileLines("inputs/day05_1.txt").toList()
        day5_2(pass)
    }

    @Test
    fun day6_1Runner() {
        val lines = getFileLines("inputs/day06_1.txt").toList()
        day6_1(lines)
    }
    @Test
    fun day6_2Runner() {
        val lines = getFileLines("inputs/day06_1.txt").toList()
        day6_2(lines)
    }

    @Test
    fun day7_1Runner() {
        val lines = getFileLines("inputs/day07_1.txt").toList()
        day7_1(lines)
    }
    @Test
    fun day7_2Runner() {
        val lines = getFileLines("inputs/day07_1.txt").toList()
        day7_2(lines)
    }

    @Test
    fun day8_1Runner() {
        val lines = getFileLines("inputs/day08_1.txt").toList()
        day8_1(lines)
    }
    @Test
    fun day8_2Runner() {
        val lines = getFileLines("inputs/day08_1.txt").toList()
        day8_2(lines)
    }

    @Test
    fun day9_1Runner() {
        val lines = getFileLines("inputs/day09_1.txt").toList()
        day9_1(lines, 25)
    }
    @Test
    fun day9_2Runner() {
        val lines = getFileLines("inputs/day09_1.txt").toList()
        day9_2(lines, 25)
    }

    @Test
    fun day10_1Runner() {
        val lines = getFileLines("inputs/day10_1.txt").toList()
        day10_1(lines)
    }
    @Test
    fun day10_2Runner() {
        val lines = getFileLines("inputs/day10_example.txt").toList()
        day10_2(lines)
    }



    fun getFileScanner(fileName: String) =
        Scanner(FileInputStream(fileName))



    fun getFileLines(fileName: String) =
        Files.readAllLines(Path.of(fileName))


}