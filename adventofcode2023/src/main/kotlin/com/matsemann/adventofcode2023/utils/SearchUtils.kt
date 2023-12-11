package com.matsemann.adventofcode2023.utils

/**
 * A general breadth first search, where you
 * can pass in the neighbor generating function,
 * and optionally a function for when to end the search
 *
 * Tracks various things during the run, and can build
 * the path between nodes
 */
class BFS<E>(val neighborFunc: BFS<E>.(E) -> Iterable<E>) {

    val Q = mutableListOf<E>()
    val visited = mutableSetOf<E>()
    val seen = mutableSetOf<E>()
    var endFound: E? = null
    var parent = mutableMapOf<E, E>()

    fun clear() {
        Q.clear()
        seen.clear()
        visited.clear()
        parent.clear()
        endFound = null
    }

    /**
     * Runs it, either until it's not possible to go any further,
     * or if provided until the goal function returns true for a node
     */
    fun solve(start: E, goalFunction: (BFS<E>.(E) -> Boolean)? = null): E? {
        clear()
        Q.add(start)
        seen.add(start)

        while (Q.isNotEmpty()) {
            val currentNode = Q.removeFirst()
            visited += currentNode
            if (goalFunction != null && this.goalFunction(currentNode)) {
                endFound = currentNode
                return currentNode
            }

            val neighbors = this.neighborFunc(currentNode)
            neighbors.forEach { neighbor ->
                if (neighbor !in seen) {
                    parent[neighbor] = currentNode
                    Q += neighbor
                    seen += neighbor
                }
            }
        }

        return null
    }

    /**
     * After a solve, returns the path from start to the node
     */
    fun path(node: E): List<E> {
        val path = mutableListOf<E>()
        var current: E? = node
        while (current != null) {
            path.addFirst(current)
            current = parent[current]
        }
        return path
    }


}

fun main() {

    data class Node(val name: String, val neighbors: List<String>)
    val nodes = listOf(
        Node("A", listOf("B", "C")),
        Node("B", listOf("A", "C")),
        Node("C", listOf("A", "B", "D", "E")),
        Node("D", listOf("C", "E")),
        Node("E", listOf("C", "D", "F")),
        Node("F", listOf("E", "G")),
        Node("G", listOf("F", "H")),
        Node("H", listOf("G")),
    )
    val start = nodes[0]

    val bfs = BFS<Node> { node ->
        nodes.filter { it.name in node.neighbors }
    }

    // Test only going to F
    val found = bfs.solve(start) {
        it.name == "F"
    }
    println("Found goal $found, expected Node F")
    println("Path to goal " + bfs.path(found!!).map { it.name } + ", expected [A, C, E, F]")
    println("Visited partial ${bfs.visited.map { it.name }}, expected [A, B, C, D, E, F]")

    // Test going until end
    bfs.solve(nodes[0])
    println("Visited full ${bfs.visited.map { it.name }}, expected [A, B, C, D, E, F, G, H]")

}