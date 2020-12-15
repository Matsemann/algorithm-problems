package com.matsemann.adventofcode2020

fun day1_1(numbers: List<Int>) {
    for (i in 0 until numbers.size - 1) {
        for (j in i+1 until numbers.size) {
            if (numbers[i] + numbers[j] == 2020) {
                println(numbers[i] * numbers[j])
                return
            }
        }
    }
}


fun day1_2(numbers: List<Int>) {
    for (i in 0 until numbers.size - 2) {
        for (j in i+1 until numbers.size-1) {
            for (k in j+1 until numbers.size) {
                if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                    println(numbers[i] * numbers[j]* numbers[k])
                    return
                }
            }
        }
    }
}
