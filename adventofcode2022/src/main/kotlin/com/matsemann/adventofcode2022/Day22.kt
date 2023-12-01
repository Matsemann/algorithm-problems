package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*

fun day22_1(lines: List<String>): Any {
    Direction.setYDown()
    val (mapLines, instructionLine) = lines.splitBy { it == "" }

    val width = mapLines.maxOf { it.length }
    val emptyLine = " ".repeat(width + 2).toList()

    val grid = listOf(emptyLine) +
            mapLines.map { (' ' + it.padEnd(width + 1, ' ')).toList() } +
            listOf(emptyLine)

    val instructions = "\\d+|\\w".toRegex().findAll(instructionLine.first()).map { it.value }.toList()

    var direction = Direction.RIGHT
    var pos = IntVec(grid[1].indexOf('.'), 1)

    for (instruction in instructions) {
        if (instruction == "L") {
            direction = direction.turnCcw()
        } else if (instruction == "R") {
            direction = direction.turnCw()
        } else {
            val toWalk = instruction.toInt()

            for (i in 1..toWalk) {
                var newPos = pos + direction

                if (grid[newPos] == ' ') {
                    if (direction == Direction.RIGHT) {
                        val newX = grid[pos.y].indexOfFirst { it != ' ' }
                        newPos = IntVec(newX, pos.y)
                    } else if (direction == Direction.LEFT) {
                        val newX = grid[pos.y].indexOfLast { it != ' ' }
                        newPos = IntVec(newX, pos.y)
                    } else if (direction == Direction.DOWN) {
                        val newY = grid.col(pos.x).indexOfFirst { it != ' ' }
                        newPos = IntVec(pos.x, newY)
                    } else if (direction == Direction.UP) {
                        val newY = grid.col(pos.x).indexOfLast { it != ' ' }
                        newPos = IntVec(pos.x, newY)
                    }
                }

                if (grid[newPos] == '#') {
                    break
                }
                pos = newPos
            }
        }
    }

    val dirScore = when(direction) {
        Direction.RIGHT -> 0
        Direction.DOWN -> 1
        Direction.LEFT -> 2
        Direction.UP -> 3
    }

    val score = (pos.y)*1000 + (pos.x)*4 + dirScore

    return score
}

fun day22_2(lines: List<String>): Any {
    return 2
}

fun main() {

    run("1", fileName = "day22_ex.txt", func = ::day22_1)
//    run("2", fileName = "day22_ex.txt", func = ::day22_2)

    run("1", fileName = "day22.txt", func = ::day22_1)
//    run("2", fileName = "day22.txt", func = ::day22_2)
}

/*
OUTPUT
======


 */