@file:OptIn(ExperimentalStdlibApi::class)

package com.matsemann.adventofcode2022

import com.matsemann.adventofcode2022.utils.*



fun day0_testing(lines: List<String>): Any {
    val lists = MutableList(5) { mutableListOf<Int>() }

    val lists2 = 4 * mutableListOf(1, 2, 3, 4, 5)

    val ok = lists2[0..1, 0..2]

    val list1 = lists2[0]
    val indices = 1 until 5 step 2
    val inds = indices.map { it }
    val test = list1.slice(indices)

    val lists3 = lists2.transpose()

    return 0
}





fun main() {

    run("1", fileName = "day05_ex.txt", func = ::day0_testing)

//    run("1", fileName = "day05.txt", func = ::day0_testing)
}

/*
Done. Took 39ms to run
Result for 1:	CMZ
Copied to clipboard!

Done. Took 1ms to run
Result for 2:	MCD
Copied to clipboard!

Done. Took 15ms to run
Result for 1:	FWNSHLDNZ
Copied to clipboard!

Done. Took 6ms to run
Result for 2:	RNRGDNFQG
Copied to clipboard!
 */