package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*

fun day05_1(lines: List<String>): Any {
    val (stacks, moves) = lines.splitBy { it == "" }

    val crates = stacks.map { crt ->
        crt.chunked(4).map { it[1] }
    }.transpose().toMutableList()

    moves.map { it.allInts() }
        .forEach { (numMoves, from, to) ->
            repeat(numMoves) {
                val popped = crates[from].removeFirst()
                crates[to].add(0, popped)
            }
        }

    return crates.map { it.first() }.joinToString("")
}


fun day05_2(lines: List<String>): Any {
    val (stacks, moves) = lines.splitBy { it == "" }
    val crates = DefaultMap<Int, _> { mutableListOf<Char>()}

    stacks.forEach { crt ->
        crt.chunked(4).map { it[1] }.mapIndexed { i, c ->
            if (c != ' ')
                crates[i].add(c)
        }
    }

    moves.map { it.allInts() }.forEach { (num, from, to) ->
        val toMove = crates[from - 1].take(num)
        crates[from-1] = crates[from-1].drop(num).toMutableList()
        crates[to - 1].addAll(0, toMove)
    }

    return crates.map { it.value.first() }.joinToString("")
}

fun main() {

    run("1", fileName = "day05_ex.txt", func = ::day05_1)
    run("2", fileName = "day05_ex.txt", func = ::day05_2)

    run("1", fileName = "day05.txt", func = ::day05_1)
    run("2", fileName = "day05.txt", func = ::day05_2)
}

/*
Done. Took 39ms to run
Result for 1:	CMZ
Copied to clipboard!

Done. Took 1ms to run
Result for 2:	MCD
Copied to clipboard!

Done. Took 15ms to run
Result for 1:	FWNSHLDNZ
Copied to clipboard!

Done. Took 6ms to run
Result for 2:	RNRGDNFQG
Copied to clipboard!
 */