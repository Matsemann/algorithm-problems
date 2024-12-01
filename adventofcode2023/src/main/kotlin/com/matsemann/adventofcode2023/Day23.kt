package com.matsemann.adventofcode2023

import com.matsemann.adventofcode2023.utils.*
import com.matsemann.adventofcode2023.utils.IntVec.Companion.allWithinBounds


data class Node(val pos: IntVec, val neighbors: MutableList<Node>)

fun day23_1(lines: List<String>): Any {
    val grid = lines.map { it.toList() }
    val bounds = grid.bounds()

    val start = IntVec(1, 0)
    val end = bounds - IntVec(1, 0)


    fun dfs(pos: IntVec, path: List<IntVec>): List<Int> {
        val newPath = path + pos
        if (pos == end) {
            return listOf(path.size)
        }

        val neighbors = when (grid[pos]) {
            '>' -> listOf(pos + Direction.RIGHT)
            '<' -> listOf(pos + Direction.LEFT)
            'v' -> listOf(pos + Direction.DOWN)
            '^' -> listOf(pos + Direction.UP)
            else -> pos.neighbors()
        }

        val nextSteps = neighbors.filter { it.withinBounds(bounds) }
            .filter { grid[it] != '#' }
            .filter { it !in newPath }

        if (nextSteps.isEmpty()) {
            return emptyList()
        }

        return nextSteps.map {
            dfs(it, newPath)
        }.flatten()
    }

    return dfs(start, listOf()).max()
}


fun day23_2(lines: List<String>): Any {
    val grid = lines.map { it.toList() }
    val bounds = grid.bounds()

    val start = IntVec(1, 0)
    val end = bounds - IntVec(1, 0)

    val nodes = mutableMapOf<IntVec, MutableSet<Pair<IntVec, Int>>>(
        start to mutableSetOf(),
        end to mutableSetOf(),
    )

    bounds.allWithinBounds().filter { pos ->
        grid[pos] != '#' && pos.neighbors(bounds).filter { grid[it] != '#' }.size > 2
    }.forEach { nodes += it to mutableSetOf() }

    nodes.forEach { (node, _) ->
        fun dfs2(pos: IntVec, path: List<IntVec>) : List<Pair<IntVec, Int>> {
            if (pos != node && pos in nodes) {
                return listOf(pos to path.size)
            }

            val neighbors = pos.neighbors(bounds)
                .filter { grid[it] != '#' }
                .filter { it !in path }

            return neighbors.map { dfs2(it, path + pos) }.flatten()
        }
        val res = dfs2(node, listOf())
        res.forEach { (nodeFound, length) ->
            nodes[node]!!.add(nodeFound to length)
        }
    }

    fun lengthFrom(from: IntVec, to: IntVec) : Int {
        return nodes[from]!!.filter { it.first == to }.maxOf { it.second }
    }

    fun dfs(pos: IntVec, path: List<IntVec>, length: Int): List<Int> {
        val newPath = path + pos
        if (pos == end) {
            return listOf(length)
        }

        val nextSteps2 = nodes[pos]!!.filter { it.first !in path }
        val b = nextSteps2.map { n ->
            dfs(n.first, newPath, length + lengthFrom(pos, n.first))
        }
        return b.flatten()
    }

    return dfs(start, listOf(), 0).max()
}

fun main() {
    Direction.setYDown()

//    run("1", fileName = "day23_ex2.txt", func = ::day23_1)
//    run("1", fileName = "day23_ex.txt", func = ::day23_1)
    run("2", fileName = "day23_ex.txt", func = ::day23_2)


//    run("1", fileName = "day23.txt", func = ::day23_1)
    run("2", fileName = "day23.txt", func = ::day23_2)
}
