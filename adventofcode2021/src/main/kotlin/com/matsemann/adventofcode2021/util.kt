package com.matsemann.adventofcode2021

import java.io.FileInputStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


fun getFileScanner(fileName: String) =
    Scanner(FileInputStream(fileName))

fun getFileLines(fileName: String) =
    Files.readAllLines(Path.of("adventofcode2021/inputs/$fileName"))

fun measure(func: () -> Unit) : Unit {
    val start = System.currentTimeMillis()
    func()
    val time = System.currentTimeMillis() - start
    print("\n\nTook ${time}ms to run")
}