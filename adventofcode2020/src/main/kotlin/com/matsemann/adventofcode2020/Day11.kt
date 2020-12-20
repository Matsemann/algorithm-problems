package com.matsemann.adventofcode2020


fun day11_1(lines: List<String>) {

    val seats = lines.map { it.toCharArray() }.toTypedArray()

    var result = Pair(true, seats)
    while(true) {
        result = runRules(result.second)
        if (!result.first) {
            break
        }
    }

    val occupied = result.second.sumBy { line -> line.count { it == '#' } }

    println("occupied: $occupied")

}

fun runRules(seats: Array<CharArray>): Pair<Boolean, Array<CharArray>> {
    val newSeats = Array(seats.size) {
        CharArray(seats[0].size)
    }

    var hasChanged = false

    for (i in 0 until seats.size) {
        for (j in 0 until seats[i].size) {
            val oldVal = seats[i][j]
            val newVal = when (oldVal) {
                'L' -> {
                    if (adjacents(seats, i, j).none { it == '#' }) {
                        '#'
                    } else {
                        'L'
                    }
                }
                '#' -> {
                    if (adjacents(seats, i, j).count { it == '#' } >= 4) {
                        'L'
                    } else {
                        '#'
                    }
                }
                else -> oldVal
            }

            hasChanged = oldVal != newVal || hasChanged
            newSeats[i][j] = newVal
        }
    }
    return Pair(hasChanged, newSeats)
}

fun adjacents(seats: Array<CharArray>, i: Int, j: Int) : List<Char> {
    val adjacents = mutableListOf<Char>()

    for (k in -1..1) {
        for (l in -1..1) {
            val x = i + k
            val y = l + j

            if (k == 0 && l == 0) continue
            if (x < 0 || x >= seats.size || y < 0 || y >= seats[x].size) continue

            adjacents.add(seats[x][y])
        }
    }

    return adjacents
}
