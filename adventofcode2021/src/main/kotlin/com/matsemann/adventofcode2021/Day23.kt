package com.matsemann.adventofcode2021

import java.util.*
import kotlin.math.abs

val hallwayStops = listOf(0, 1, 3, 5, 7, 9, 10)
val doors = listOf(2, 4, 6, 8)
val room1 = listOf(11, 12)
val room2 = listOf(13, 14)
val room3 = listOf(15, 16)
val room4 = listOf(17, 18)
val rooms = listOf(room1, room2, room3, room4)
val costs = listOf(1, 10, 100, 1000)

data class State(val positions: List<Int>) {

    fun isFinished(): Boolean {
        return positions[0] in room1
                && positions[1] in room1
                && positions[2] in room2
                && positions[3] in room2
                && positions[4] in room3
                && positions[5] in room3
                && positions[6] in room4
                && positions[7] in room4
    }

    fun getPossibleMoves() : List<Pair<Int, State>> {
        return positions.indices.flatMap { getMoves(it) }
    }

    private fun getMoves(amphi: Int) : List<Pair<Int, State>> {
        val pos = positions[amphi]
        val goalRoom = amphi / 2
        val goalRoomSpots = rooms[goalRoom]

        if (pos > 10) { // in a room
            // don't make a move if in the correct spot
//            if (pos in goalRoomSpots) {
//                if (pos == goalRoomSpots[1]) { // In the back, ok
//                    return listOf()
//                } else {
//                    // In the front, but the one behind is ok
//                }
//            }


            val isInTheBack = pos % 2 == 0
            if (isInTheBack) {
                val isBlocked = positions.any { it == pos - 1 } // fix for 4
                if (isBlocked) {
                    return listOf()
                }
            }

            val currentRoom = (pos - 11) / 2
            val entrance = doors[currentRoom]
            val stepToEntrance = if (isInTheBack) 2 else 1 // fix for 4
            val cost = costs[goalRoom]

            val moves = hallwayStops.mapNotNull { stop ->
                if (canGoTo(to=stop, from=entrance)) {
                    val dst = abs(entrance - stop)
                    val totalCost = cost * (stepToEntrance + dst)
                    val newState = positions.toMutableList()
                    newState[amphi] = stop
                    totalCost to State(newState)
                } else {
                    null
                }
            }
            return moves
        } else { // outside
            val doorPos = doors[goalRoom]
            val canGoToDoor = canGoTo(pos, doorPos)
            if (!canGoToDoor) {
                return listOf()
            }

            val anyoneNotSupposedInTheRoom = positions.filterIndexed { i, amphiPos ->
                amphiPos in goalRoomSpots && i / 2 != goalRoom // fix for 4
            }

            if (anyoneNotSupposedInTheRoom.any()) {
                return listOf()
            }

            val otherIsThereAlready = positions.any {
                it in goalRoomSpots
            }

            val stepsFromEntrance = if (otherIsThereAlready) 1 else 2 // fix for 4
            val stepsToEntrance = abs(pos - doorPos)
            val finalPos = if (otherIsThereAlready) goalRoomSpots[0] else goalRoomSpots[1] // fix for 4
            val cost = costs[goalRoom]

            val totalCost = cost * (stepsToEntrance + stepsFromEntrance)

            val newState = positions.toMutableList()
            newState[amphi] = finalPos
            return listOf(totalCost to State(newState))
        }
    }

    private fun canGoTo(from: Int, to: Int): Boolean {
        val range = minOf(from+1, to)..maxOf(from-1, to)
        return !positions.any { pos -> pos in range }
    }
}

fun day23_1(lines: List<String>): Any {
//    val startState = listOf(room1[1], room4[1], room1[0], room3[0], room2[0], room3[1], room2[1], room4[0])
    val startState = listOf(room2[1], room4[0], room1[0], room2[0], room1[1], room4[1], room3[0], room3[1])


//    val startState = listOf(room1[1], room4[1], room1[0], 3, 9, room3[1], room2[1], room4[0])

    val start = State(startState)

    val visited = mutableSetOf<State>()
    val queue = PriorityQueue<Pair<Int, State>>(Comparator.comparing { it.first })
    queue.offer(0 to start)

    while (queue.isNotEmpty()) {
        val u = queue.poll()

        if (u.second.isFinished()) {
            return u.first
        }
        if (u.second in visited) {
            continue
        }
        visited.add(u.second)

        u.second.getPossibleMoves().filterNot { it.second in visited }.forEach { (cost, state) ->
            val totalCost = u.first + cost
            queue.offer(totalCost to state)
        }

    }

//    val moves = start.getPossibleMoves()
//
    return "failed"

    // is goal state

    // for each amphipod, generate moves
    // if inside
    //      and nothing in front
    //      move out to all feasible spots
    //      where feasible is all left/right of entrance not blocked
    //      costs are moving out + dist from entrance
    // if outside, only 0 or 1 move possible
    //      which is going in, or is blocked
}


fun day23_2(lines: List<String>): Any {
    return 2
}

fun main() {

    run("1", fileName = "day23_dummy.txt", func = ::day23_1)
//    run("1", fileName = "day23_1.txt", func = ::day23_1)
//    run("2", fileName = "day23_ex.txt", func = ::day23_2)
//    run("2", fileName = "day23_1.txt", func = ::day23_2)
}

/*
OUTPUT
======

Done. Took 27ms to run
Result for 1:	1462

Done. Took 37ms to run
Result for 2:	1497

 */