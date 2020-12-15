package com.matsemann.adventofcode2020


fun day8_1(lines: List<String>) {
    val instructions = lines.map {
        val split = it.split(" ")
        Instruction(split[0], split[1].toInt())
    }

    val program = Program(instructions)
    program.execute()

    println("end state: ${program.accumulator}")
}

fun day8_2(lines: List<String>) {
    val instructions = lines.map {
        val split = it.split(" ")
        Instruction(split[0], split[1].toInt())
    }

    for (i in instructions.indices) {
        val newInstructions = instructions.toMutableList()
        val instruction = newInstructions[i]
        val newInstruction =
            if (instruction.first == "jmp") {
                instruction.copy(first = "nop")
            } else if (instruction.first == "nop") {
                instruction.copy(first = "jmp")
            } else {
                continue
            }

        newInstructions[i] = newInstruction

        val program = Program(newInstructions)
        if (program.execute()) {
            println("finished ok, ${program.accumulator}")
            break
        }
    }

}

class Program(
    val instructions: List<Instruction>
) {
    var accumulator: Int = 0
    var instructionPointer: Int = 0
    val seenInstructions = mutableSetOf<Int>()

    fun execute() : Boolean {
        while (true) {
            if (seenInstructions.contains(instructionPointer)) {
                return false // loops
            }
            if (instructionPointer == instructions.size) {
                return true // reached end
            }
            seenInstructions.add(instructionPointer)
            val instruction = instructions[instructionPointer]

            when(instruction.first) {
                "acc" -> {
                    accumulator += instruction.second
                    instructionPointer++
                }
                "jmp" -> {
                    instructionPointer += instruction.second
                }
                else -> {
                    instructionPointer++
                }
            }
        }
    }

}

typealias Instruction = Pair<String, Int>