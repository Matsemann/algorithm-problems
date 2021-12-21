package com.matsemann.adventofcode2021

fun solve(lines: List<String>, days: Int): Any {
    // Solves it in linear time ( O(days) ) by only keeping
    // count of totals, not modeling each fish
    val state = Counter<Int>()

    lines.first()
        .split(",")
        .map { it.toInt() }
        .forEach { state[it]++ }

    for (d in 1..days) {
        val newFishes = state[0]
        for (i in 0..7) {
            state[i] = state[i+1]
        }
        state[8] = newFishes
        state[6] += newFishes
    }

    return state.values.sumOf { it }
}



fun main() {
    for (i in 0..1) {
        run("1", fileName = "day06_1.txt") {
            solve(it, 80)
        }
        run("2", fileName = "day06_1.txt") {
            solve(it, 256)
        }
    }
}

/*
OUTPUT
======

450000 ~= 800ms

Done. Took 0ms to run
Result for 1:	350149
Copied to clipboard!

Done. Took 0ms to run
Result for 2:	1590327954513
Copied to clipboard!

 */