package com.matsemann.adventofcode2021

fun day02_1(lines: List<String>): Any {
    var x = 0
    var y = 0
    lines
        .map { it.split(" ") }
        .map { Pair(it[0], it[1].toInt()) }
        .forEach { (dir, amount) ->
            when (dir) {
                "forward" -> x += amount
                "down" -> y += amount
                "up" -> y -= amount
            }
        }

    return x * y
}


fun day02_2(lines: List<String>): Any {
    var aim = 0
    var x = 0
    var y = 0
    lines
        .map { it.split(" ") }
        .map { Pair(it[0], it[1].toInt()) }
        .forEach { (dir, amount) ->
            when (dir) {
                "forward" -> {
                    x += amount
                    y += aim * amount
                }
                "down" -> aim += amount
                "up" -> aim -= amount
            }
        }

    return x * y
}

fun main() {
    run("1", fileName = "day02_1.txt", func = ::day02_1)
    run("2", fileName = "day02_1.txt", func = ::day02_2)
}

/*
OUTPUT
======

Done. Took 4ms to run
Result for 1:	1728414

Done. Took 2ms to run
Result for 2:	1765720035

 */