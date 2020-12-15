package com.matsemann.adventofcode2020

fun day2_1(lines: List<String>) {
    val validPasswords = lines.count {
        val parts = it.split(" ")
        val rangeParts = parts[0].split("-")

        passWordChecker(parts[1][0], rangeParts[0].toInt(), rangeParts[1].toInt(), parts[2])
    }
    println("valid: $validPasswords")
}

fun passWordChecker(letter: Char, min: Int, max: Int, password: String): Boolean {
    val occurences = password.count { char -> char == letter }
    return occurences in min..max
}


fun day2_2(lines: List<String>) {
    val validPasswords = lines.count {
        val parts = it.split(" ")
        val rangeParts = parts[0].split("-")

        passWordChecker2(parts[1][0], rangeParts[0].toInt(), rangeParts[1].toInt(), parts[2])
    }
    println("valid: $validPasswords")
}


fun passWordChecker2(letter: Char, pos1: Int, pos2: Int, password: String): Boolean {
    return (password[pos1-1] == letter) xor (password[pos2-1] == letter)
}
