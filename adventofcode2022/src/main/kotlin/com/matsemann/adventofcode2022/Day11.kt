package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*
import java.math.BigInteger


class Operation(val expression: String) {

    operator fun invoke(old: BigInteger): BigInteger {
        val parts = expression.split(" ")
        val part1 = if (parts[0] == "old") {
            old
        } else {
            parts[0].toBigInteger()
        }
        val part2 = if (parts[2] == "old") {
            old
        } else {
            parts[2].toBigInteger()
        }
        return when(parts[1]) {
            "*" -> part1 * part2
            else -> part1 + part2
        }
    }
}

data class Monkey(val test:BigInteger, val ifTrue:Int, val ifFalse:Int, val operation: Operation, val items: MutableList<BigInteger>)

fun day11_1(lines: List<String>): Any {
    val monkeys = lines.splitBy { it.isBlank() }.map { it ->
        Monkey(
            items = it[1].allInts().map { it.big() }.toMutableList(),
            operation = Operation(it[2].substringAfter("= ")),
            test = it[3].firstInt().big(),
            ifTrue = it[4].firstInt(),
            ifFalse = it[5].firstInt()
        )
    }

    val inspections = Counter<Int>()
    repeat(20) {
        monkeys.forEachIndexed { index, monkey ->
            monkey.items.forEach { item ->
                val worry = monkey.operation(item) / 3.big()
                if (worry % monkey.test == 0.big()) {
                    monkeys[monkey.ifTrue].items.add(worry)
                } else {
                    monkeys[monkey.ifFalse].items.add(worry)
                }
                inspections[index]++
            }
            monkey.items.clear()
        }

    }
    println(inspections.map)
    return inspections.values.sortedDescending().take(2).let { it[0] * it[1] }
}


fun day11_2(lines: List<String>): Any {
    val monkeys = lines.splitBy { it.isBlank() }.mapIndexed { index, it ->
        Monkey(
            it[3].allInts()[0].big(),
            it[4].allInts()[0],
            it[5].allInts()[0],
            Operation(it[2].substringAfter("= ")),
            it[1].allInts().map { it.big() }.toMutableList()
        )
    }

    // Make up my own divisor to keep it from growing
    val divisor = monkeys.fold(1.big()) {acc, monkey -> acc * monkey.test }

    val inspections = Counter<Int>()
//    val inspections = Array(10) {0}
    repeat(10_000) {
        monkeys.forEachIndexed { index, monkey ->
            monkey.items.forEach { item ->
                val worry = monkey.operation(item) % divisor

                if (worry % monkey.test == 0.big()) {
                    monkeys[monkey.ifTrue].items.add(worry)
                } else {
                    monkeys[monkey.ifFalse].items.add(worry)
                }
                inspections[index]++
            }
            monkey.items.clear()
        }

    }
//    println(inspections.map)
    return inspections.values.sortedDescending().take(2).let { it[0] * it[1] }
}

fun main() {

//    run("1", fileName = "day11_ex.txt", func = ::day11_1)
    run("2", fileName = "day11_ex.txt", func = ::day11_2)

//    run("1", fileName = "day11.txt", func = ::day11_1)
        run("2", fileName = "day11.txt", func = ::day11_2)
}

/*
OUTPUT
======

{0=52166, 1=47830, 2=1938, 3=52013}
Done. Took 216ms to run
Result for 2:	2713310158
Copied to clipboard!

{0=33497, 1=115568, 2=120360, 3=120377, 4=33447, 5=28580, 6=33435, 7=148916}
Done. Took 242ms to run
Result for 2:	17926061332
Copied to clipboard!

 */