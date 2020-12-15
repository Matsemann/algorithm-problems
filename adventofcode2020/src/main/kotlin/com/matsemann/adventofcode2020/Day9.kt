package com.matsemann.adventofcode2020

import java.math.BigInteger


fun day9_1(lines: List<String>, preamble: Int) {
    val numbers = lines.map { it.toBigInteger() }

    val firstInvalid = findFirstInvalid(numbers, preamble)
    println("first invalid: $firstInvalid")

}

fun day9_2(lines: List<String>, preamble: Int) {
    val numbers = lines.map { it.toBigInteger() }

    val invalid = findFirstInvalid(numbers, preamble)

    // Seeing if I can do it in n and not n^2 !!
    var low = 0
    var high = 0
    var sum = BigInteger.ZERO

    while(true) {
        if (sum < invalid) {
            sum += numbers[high]
            high++
        } else if (sum > invalid) {
            sum -= numbers[low]
            low++
        } else {
            break
        }
    }

    val summings = numbers.subList(low, high)
    val max = summings.maxOrNull() ?: BigInteger.ZERO
    val min = summings.minOrNull() ?: BigInteger.ZERO

    println("result: ${max + min}")

}

fun findFirstInvalid(numbers: List<BigInteger>, preamble: Int): BigInteger {
    for (i in preamble until numbers.size) {
        val num = numbers[i]
        if (!anyTwoSumsTo(numbers.subList(i - preamble, i), num)) {
            return num
        }
    }
    return BigInteger.ZERO
}

fun anyTwoSumsTo(list: List<BigInteger>, target: BigInteger) : Boolean {
    for (i in 0 until list.size - 1) {
        for (j in i+1 until list.size) {
            if (list[i] != list[j] && list[i] + list[j] == target) {
                return true
            }
        }
    }
    return false
}
