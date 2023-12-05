package com.matsemann.adventofcode2023

import com.matsemann.adventofcode2023.utils.*
import java.math.BigInteger
import kotlin.math.max
import kotlin.math.min

fun day05_1(lines: List<String>): Any {
    val seeds = lines.first().split(" ").longs()
    val categories = lines
        .drop(2)
        .splitBy { it == "" }
        .map { maps ->
            maps.drop(1).map {
                it.split(" ").longs()
            }
        }

    return seeds.map { seed ->
        categories.fold(seed) { current, mappings ->
            mappings.find { mapping ->
                val openEndRange = mapping[1]..<mapping[1] + mapping[2]
                current in openEndRange
            }?.let {
                val diff = current - it[1]
                val newValue = it[0] + diff
                newValue
            } ?: current
        }
    }.min()
}


fun day05_2(lines: List<String>): Any {
    val seedRanges = lines.first().split(" ").longs()
        .chunked(2).map { it[0]..<it[0]+it[1] }
    val categories = lines
        .drop(2)
        .splitBy { it == "" }
        .map { maps ->
            maps.drop(1).map {
                it.split(" ").longs()
            }.map { (source, start, num) ->
                source to start..<start+num
            }
        }


    return seedRanges.map { seedRange ->
        categories.fold(listOf(seedRange)) { ranges, mappings ->

            val rangesToMap = ranges.toMutableList()
            val mappedRanges = mutableListOf<LongRange>()

            while (rangesToMap.isNotEmpty()) {
                val range = rangesToMap.removeFirst()

                val mapping = mappings.find { (_, mapRange) ->
                    range.overlaps(mapRange)
                }

                if (mapping == null) {
                    mappedRanges += range
                    continue
                }

                val overlap = mapping.second.intersect(range)
                val diff = mapping.first - mapping.second.first
                mappedRanges += (overlap.first+diff)..(overlap.last+diff)

                val remaining = range - overlap
                rangesToMap.addAll(remaining)
            }

            mappedRanges
        }
    }
        .flatten()
//        .println()
        .minOf { it.first }


}

fun main() {

//    run("1", fileName = "day05_ex.txt", func = ::day05_1)
    run("2", fileName = "day05_ex.txt", func = ::day05_2)


//    run("1", fileName = "day05.txt", func = ::day05_1)
//    repeat(400) {
        run("2", fileName = "day05.txt", func = ::day05_2)
//    }
}
