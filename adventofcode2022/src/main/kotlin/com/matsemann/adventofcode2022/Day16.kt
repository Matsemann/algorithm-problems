package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*

data class Valve(val name: String, var tunnelsTo: List<Valve> = mutableListOf(), var flowRate: Int = 0) {
    override fun toString(): String {
        return "Valve(name='$name')"
    }
}

fun day16_1(lines: List<String>): Any {
    val valves = DefaultMap<String, _> { Valve(it) }
    lines.map {
        val parts = it.split("; ")
        val tunnels = parts[1].substringAfter(" to ").split(" ").drop(1)

        val valve = valves[parts[0].drop(6).take(2)]
        valve.flowRate = parts[0].allInts()[0]
        valve.tunnelsTo = tunnels.map { tunnel -> valves[tunnel.take(2)] }
    }


    fun score(valve: Valve, seenValves: Set<String>, openValves: Set<String>, roundsLeft: Int): Int {
        if (roundsLeft <= 0) {
            return 0
        }
        val scores = mutableListOf<Int>()
        // If we stay and open the valve
        if (valve.name !in openValves && valve.flowRate > 0) {
            val nowOpenValves = openValves + valve.name
            val nowSeenValves = setOf(valve.name)
            val lifeTimeValue = (roundsLeft - 1) * valve.flowRate
            for (v in valve.tunnelsTo) {
                val theirScore = score(v, nowSeenValves, nowOpenValves, roundsLeft - 2)
                scores.add(lifeTimeValue + theirScore)
            }
        }
        // If we just move on
        val nowSeenValves = seenValves + valve.name
        for (v in valve.tunnelsTo) {
            if (v.name !in seenValves) { // avoid stupid loops
                val theirScore = score(v, nowSeenValves, openValves, roundsLeft - 1)
                scores.add(theirScore)
            }
        }
        if (scores.isEmpty()) return 0
        return scores.max()
    }

    return score(valves["AA"], setOf(), setOf(), 30)

}

// curl https://adventofcode.com/2022/day/16/input --cookie "session=53616c7465645f5f2630ea26d062e2de9f48908da3c3bbaaf15c6db74787d57c5ad60d335d8edfb91c1ab38b67a8376cae6e511e09ee56fc16421161991e86ea" > inputs/day16.txt



fun scoreElephant(
    newValve: Valve,
    newSeenValves: Set<String>,
    elephantValve: Valve,
    elephantSeenValves: Set<String>,
    openValves: Set<String>,
    roundsLeft: Int,
    elephantCanOpen: Boolean,
    valvesToOpen: Int
): Pair<Int, List<String>> {
    var max = 0
    var maxPath = listOf<String>()

    val newElephantSeenValves = elephantSeenValves + elephantValve.name
    for (ev in elephantValve.tunnelsTo) {
        if (ev.name !in elephantSeenValves) {
            if (roundsLeft == 26) {
                println("EF moving ${ev.name}")
            }
            val (score, path) = score(
                valve = newValve,
                seenValves = newSeenValves,
                elephantValve = ev,
                elephantSeenValves = newElephantSeenValves,
                openValves = openValves,
                roundsLeft = roundsLeft - 1,
                valvesToOpen = valvesToOpen
            )

            if (score > max) {
                max = score
                maxPath = path + "E Move ${ev.name}"
            }
        }
    }
    if (elephantCanOpen) {
        val newOpenValves = openValves + elephantValve.name
        val openScore = (roundsLeft - 1) * elephantValve.flowRate
        val (score, path) = score(
            valve = newValve,
            seenValves = newSeenValves,
            elephantValve = elephantValve,
            elephantSeenValves = setOf(),
            openValves = newOpenValves,
            roundsLeft = roundsLeft - 1,
            valvesToOpen = valvesToOpen
        )
        if (score + openScore > max) {
            max = score + openScore
            maxPath = path + "E Open ${elephantValve.name}"
        }
    }


    return max to maxPath

}

data class State(val valve: String, val elephantValve: String, val openValves: Set<String>, val roundsLeft: Int)
val cache = mutableMapOf<State, Pair<Int,List<String>>>()

fun score(
    valve: Valve,
    seenValves: Set<String>,
    elephantValve: Valve,
    elephantSeenValves: Set<String>,
    openValves: Set<String>,
    roundsLeft: Int,
    valvesToOpen: Int
): Pair<Int, List<String>> {


    if (roundsLeft <= 0) {
        return 0 to listOf()
    }
    if (openValves.size == valvesToOpen) {
        return 0 to listOf()
    }
//    val state = State(valve.name, elephantValve.name, openValves, roundsLeft)
//    val c = cache[state]
//    if (c != null) {
//        println("found in cache")
//        return c
//    }

    val elephantCanOpen =
        elephantValve.flowRate > 0 && elephantValve.name != valve.name && elephantValve.name !in openValves

    var max = 0
    var maxPath = listOf<String>()

    // Move
    val newSeenValves = seenValves + valve.name
    for (v in valve.tunnelsTo) {
        if (v.name !in seenValves) {
            val (score, path) = scoreElephant(
                v,
                newSeenValves,
                elephantValve,
                elephantSeenValves,
                openValves,
                roundsLeft,
                elephantCanOpen,
                valvesToOpen
            )
            if (score > max) {
                max = score
                maxPath = path + "Me Move ${v.name}"
            }
        }
    }

    // Open
    if (valve.flowRate > 0 && valve.name !in openValves) {
        val newOpenValves = openValves + valve.name
        val openScore = (roundsLeft - 1) * valve.flowRate
        val (score, path) = scoreElephant(
            valve,
            setOf(),
            elephantValve,
            elephantSeenValves,
            newOpenValves,
            roundsLeft,
            elephantCanOpen,
            valvesToOpen
        )

        if (score + openScore > max) {
            max = score + openScore
            maxPath = path + "Me Open ${valve.name}"
        }

    }

//    cache[state] = max to maxPath

    return max to maxPath
}


fun day16_2(lines: List<String>): Any {
    val valves = DefaultMap<String, _> { Valve(it) }
    lines.map {
        val parts = it.split("; ")
        val tunnels = parts[1].substringAfter(" to ").split(" ").drop(1)

        val valve = valves[parts[0].drop(6).take(2)]
        valve.flowRate = parts[0].allInts()[0]
        valve.tunnelsTo = tunnels.map { tunnel -> valves[tunnel.take(2)] }
    }

    val valvesToOpen = valves.values.filter { it.flowRate > 0 }.count()

    val (score, path) = score(valves["AA"], setOf(), valves["AA"], setOf(), setOf(), 26, valvesToOpen)

//    path.reversed().chunked(2).forEachIndexed { i, it ->
//        println(i+1)
//        println(it[0])
//        println(it[1])
//        println()
//    }


    return score
}


fun main() {

//    run("1", fileName = "day16_ex.txt", func = ::day16_1)
    run("2", fileName = "day16_ex.txt", func = ::day16_2)

//    run("1", fileName = "day16.txt", func = ::day16_1)
//    run("2", fileName = "day16.txt", func = ::day16_2)
}

/*
OUTPUT
======


 */