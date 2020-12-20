package com.matsemann.adventofcode2020

import kotlin.math.abs


fun day12_1(lines: List<String>) {

    var d = Direction.EAST
    var x = 0
    var y = 0

    lines.forEach {
        val action = it[0]
        val num = it.drop(1).toInt()
        when (action) {
            'N' -> y += num
            'S' -> y -= num
            'E' -> x += num
            'W' -> x -= num
            'F' -> {
                x += d.x * num
                y += d.y * num
            }
            'L' -> {
                for (i in 0 until num / 90) {
                    d = d.nextLeft()
                }
            }
            'R' -> {
                for (i in 0 until num / 90) {
                    d = d.nextRight()
                }
            }
        }
    }

    val manhattan = abs(x) + abs(y)
    println("manhattan: $manhattan")

}


enum class Direction(val x: Int, val y: Int) {
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0),
    NORTH(0, 1);

    fun nextRight() =
        when (this) {
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
            NORTH -> EAST
        }

    fun nextLeft() =
        when (this) {
            EAST -> NORTH
            SOUTH -> EAST
            WEST -> SOUTH
            NORTH -> WEST
        }
}