package com.matsemann.adventofcode2020

import java.math.BigInteger

fun day3_1(mapLines: List<String>) {
    val trees = traverseAndCountTrees(mapLines, 3, 1)
    println("Trees encountered: $trees")

}

fun day3_2(mapLines: List<String>) {
    val trees1 = traverseAndCountTrees(mapLines, 1, 1)
    val trees2 = traverseAndCountTrees(mapLines, 3, 1)
    val trees3 = traverseAndCountTrees(mapLines, 5, 1)
    val trees4 = traverseAndCountTrees(mapLines, 7, 1)
    val trees5 = traverseAndCountTrees(mapLines, 1, 2)
    val mul = trees1 * trees2 * trees3 * trees4 * trees5
    println("Trees encountered multiplied: $mul")

}

fun traverseAndCountTrees(mapLines: List<String>, left: Int, down: Int): BigInteger {
    val width = mapLines[0].length
    val height = mapLines.size

    var posX = 0
    var trees = 0

    for (posY in 0 until height step down) {
        val char = mapLines[posY][posX]
//        println("Checking line $posY, row $posX: $char")
        if (char == '#') {
            trees++
        }
        posX = (posX + left) % width
    }

    return trees.toBigInteger()
}

