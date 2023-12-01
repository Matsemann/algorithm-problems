package com.matsemann.adventofcode2022.utils

import kotlin.math.abs

/**
 * Split a list based on a predicate, similar to a string split
 */
fun <E> List<E>.splitBy(predicate: (E) -> Boolean): List<List<E>> =
    this.fold(mutableListOf(mutableListOf<E>())) { acc, element ->
        if (predicate.invoke(element)) {
            acc += mutableListOf<E>()
        } else {
            acc.last() += element
        }
        acc
    }

/**
 * Rotates the list num indexes
 * Example:
 * num=2, [1, 2, 3, 4] => [3, 4, 1, 2]
 * num=-1, [1, 2, 3, 4] => [4, 1, 2, 3]
 */
fun <E> List<E>.rotate(num: Int): List<E> {
    return if (num >= 0) {
        val n = num % this.size
        this.drop(n) + this.take(n)
    } else {
        val n = -num % this.size
        this.takeLast(n) + this.dropLast(n)
    }
}

/**
 * Flips rows/columns, so
 * a b c => a d
 * d e f    b e
 *          c f
 */
fun <E> List<List<E>>.transpose(): List<List<E>> =
    List(this[0].size) { i ->
        List(this.size) { j ->
            this[j][i]
        }
    }

/**
 * Indexes into a column instead of a row,
 * returns the column as a list.
 * Assumes list is rectangle/equal length each row
 */
fun <E> List<List<E>>.col(col: Int): List<E> =
    this.map { it[col] }

/**
 * Converts a 2D list to mutable
 */
fun <E> List<List<E>>.toMutableList(): MutableList<MutableList<E>> =
    MutableList(this.size) { i ->
        this[i].toMutableList()
    }

/**
 * If index > end start again from the front
 */
fun <E> List<E>.circular(index: Int) : E = this[index.mod(this.size)]
