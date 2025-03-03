package com.matsemann.adventofcode2023

import com.matsemann.adventofcode2023.utils.*
import java.math.BigInteger

fun day07_1(lines: List<String>): Any {
    return lines.map { line ->
        val parts = line.split(" ")
        val cards = parts[0].toList().map {
            when (it) {
                'T' -> 'A'
                'J' -> 'B'
                'Q' -> 'C'
                'K' -> 'D'
                'A' -> 'E'
                else -> it
            }
        }
        cards to parts[1].toInt()
    }
        .sortedWith(compareBy({ (hand, _) ->
            val pairs = hand.groupBy { it }.values.map { it.size }.sortedDescending()

            when {
                pairs[0] == 5 -> 7
                pairs[0] == 4 -> 6
                pairs[0] == 3 && pairs[1] == 2 -> 5
                pairs[0] == 3 -> 4
                pairs[0] == 2 && pairs[1] == 2 -> 3
                pairs[0] == 2 -> 2
                else -> 1
            }
        }, { (hand, _) -> hand.toString() }))
        .mapIndexed { i, (cards, bet) ->
            bet * (i + 1)
        }.sum()
}


fun day07_2(lines: List<String>): Any {
    return lines.map { line ->
        val parts = line.split(" ")
        val cards = parts[0].toList().map {
            when (it) {
                'T' -> 'A'
                'J' -> '0'
                'Q' -> 'C'
                'K' -> 'D'
                'A' -> 'E'
                else -> it
            }
        }
        cards to parts[1].toInt()
    }
        .sortedWith(compareBy({ (hand, _) ->
            val jokers = hand.count { it == '0' }
            val pairs = hand.filter { it != '0' }.groupBy { it }.values.map { it.size }.sortedDescending()

            when {
                jokers == 5 || pairs[0] + jokers == 5 -> 7
                pairs[0] + jokers == 4 -> 6
                pairs[0] + jokers == 3 && pairs[1] == 2 -> 5
                pairs[0] + jokers == 3 -> 4
                pairs[0] + jokers == 2 && pairs[1] == 2 -> 3
                pairs[0] + jokers == 2 -> 2
                else -> 1
            }
        }, { (hand, _) -> hand.toString() }))
        .mapIndexed { i, (cards, bet) ->
            bet * (i + 1)
        }.sum()
}

fun score() {
    val counter = Counter<Int>()
    val cards = (4 * listOf("2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A")).flatten()

    repeat(10_000_000) {
        val draw = cards.shuffled().take(5)
        val pairs = draw.groupBy { it }.values.map { it.size }.sortedDescending()

        val score = when {
            pairs[0] == 5 -> 7
            pairs[0] == 4 -> 6
            pairs[0] == 3 && pairs[1] == 2 -> 5
            pairs[0] == 3 -> 4
            pairs[0] == 2 && pairs[1] == 2 -> 3
            pairs[0] == 2 -> 2
            else -> 1
        }
        counter[score]++
    }

    counter.toList().sortedBy { it.first }.println()
}


fun main() {

//    run("1", fileName = "day07_ex.txt", func = ::day07_1)
//    run("2", fileName = "day07_ex.txt", func = ::day07_2)

    score()
// 249620106
//    run("1", fileName = "day07.txt", func = ::day07_1)
//    run("2", fileName = "day07.txt", func = ::day07_2)
}
