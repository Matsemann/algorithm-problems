package com.matsemann.adventofcode2020

fun day5_1(pass: List<String>) {

    val seatsMax = pass.map { decodePassString(it) }
        .map { it.first * 8 + it.second }
        .maxOrNull() ?: 0


    println("max: $seatsMax")
}

fun day5_2(pass: List<String>) {

    val seatIds = pass.map { decodePassString(it) }
        .map { it.first * 8 + it.second }
        .sorted()

    val maxId = 127 * 8 + 8

    val missing = (0..maxId)
        .filterNot { seatIds.contains(it) }

    val mySeat = missing.find {
        seatIds.contains(it - 1) && seatIds.contains(it + 1)
    }

    println("my seat id is $mySeat")


}

fun decodePassString(passString: String): Pair<Int, Int> {
    val rowString = passString.substring(0, 7)
    val seatString = passString.takeLast(3)
    val row = decodeRow(rowString)
    val seat = decodeColumn(seatString)

    return Pair(row, seat)
}

fun decodeRow(rowString: String): Int {
    return rowString
        .replace("F", "0")
        .replace("B", "1")
        .toInt(2)
}


fun decodeColumn(seatString: String): Int {
    return seatString
        .replace("L", "0")
        .replace("R", "1")
        .toInt(2)
}