package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*
import com.matsemann.adventofcode2022.utils.SliceType.Companion.end

fun day08_1(lines: List<String>): Any {
    val trees = lines.map { it.split("").ints() }

    fun visibles(ints: List<Int>): List<Boolean> {
        var highest = -1
        return ints.map {
            if (it > highest) {
                highest = it
                true
            } else {
                false
            }
        }
    }

    val visibleLeft = trees.map {visibles(it) }
    val visibleRight = trees.map {visibles(it.reversed()).reversed() }

    val transposed = trees.transpose()
    val visibleTop = transposed.map {visibles(it) }.transpose()
    val visibleBottom = transposed.map {visibles(it.reversed()).reversed() }.transpose()


    val mapped = trees.mapIndexed {index, ints ->
        ints.filterIndexed { jindex, i ->
            visibleLeft[index][jindex] || visibleRight[index][jindex]
                    || visibleTop[index][jindex] || visibleBottom[index][jindex]
        }
    }

    return mapped.map { it.size }.sum()
}


fun day08_2(lines: List<String>): Any {
    val trees = lines.map { it.split("").ints() }

    fun visibles(height: Int, ints: List<Int>): Int {
        val blocked = ints.indexOfFirst { it >= height }
        return if (blocked == -1) {
            ints.size
        } else {
            blocked + 1
        }
    }

    val scores = trees.mapIndexed { row, treeRow ->
        treeRow.mapIndexed { col, tree ->
            val right = visibles(tree, treeRow.drop(col + 1))
            val left = visibles(tree, treeRow.take(col).reversed())
            val top = visibles(tree, trees.col(col).drop(row+1))
            val bottom = visibles(tree, trees.col(col).take(row).reversed())
            left*right*top*bottom
        }
    }


    return scores.map { it.max() }.max()
}
@OptIn(ExperimentalStdlibApi::class)
fun day08_2_2(lines: List<String>): Any {
    val trees = lines.map { it.split("").ints() }

    fun visibles(height: Int, ints: List<Int>): Int {
        val blocked = ints.indexOfFirst { it >= height }
        return if (blocked == -1) {
            ints.size
        } else {
            blocked + 1
        }
    }

    // Using crazy python like indexing
    val scores = trees.mapIndexed { row, treeRow ->
        treeRow.mapIndexed { col, tree ->
            val right = visibles(tree, treeRow[col+1..<end])
            val left = visibles(tree, trees[row][col-1 downTo 0])
            val top = visibles(tree, trees[row+1..<end, col])
            val bottom = visibles(tree, trees[row-1 downTo 0, col])
            left*right*top*bottom
        }
    }


    return scores.map { it.max() }.max()
}

fun main() {

//    run("1", fileName = "day08_ex.txt", func = ::day08_1)
    run("1", fileName = "day08.txt", func = ::day08_1)

//    run("2", fileName = "day08_ex.txt", func = ::day08_2)
    run("2", fileName = "day08.txt", func = ::day08_2)
    run("2", fileName = "day08.txt", func = ::day08_2_2)
}

/*
OUTPUT
======
Done. Took 73ms to run
Result for 1:	1703
Copied to clipboard!

Done. Took 52ms to run
Result for 2:	496650
Copied to clipboard!

 */