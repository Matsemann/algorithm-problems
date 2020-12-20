package com.matsemann.adventofcode2020



fun day13_1(lines: List<String>) {

    val time = lines[0].toInt()
    val buses = lines[1].split(",")
        .filter { it != "x" }
        .map { it.toInt() }

    val firstDepartureAfterTime = buses
        .map {
        var depTime = it
        while (depTime < time)
            depTime += it
        Pair(it, depTime)
    }

    val earliest = firstDepartureAfterTime.minByOrNull { it.second }!!

    val result = (earliest.second - time) * earliest.first

    println("result: $result")


}

