package com.matsemann.adventofcode2020

fun day4_1(passportLines: List<String>) {
    val count = passportLines.joinToString("\n").split("\n\n")
        .map { chunk -> chunk.replace("\n", " ") }
        .map { line -> line.split(" ") }
        .map { parts -> Passport(parts) }
        .count { it.hasRequiredFields() }

    println("$count valid passports")
}

fun day4_2(passportLines: List<String>) {
    val count = passportLines.joinToString("\n").split("\n\n")
        .map { chunk -> chunk.replace("\n", " ") }
        .map { line -> line.split(" ") }
        .map { parts -> Passport(parts) }
        .filter { it.hasRequiredFields() }
        .count { it.fieldsAreValid() }

    println("$count valid passports")
}

data class Passport(val fields: List<String>) {
    val required = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

    fun hasRequiredFields(): Boolean {
        return required.all { requirement ->
            this.fields.any { it.startsWith(requirement) }
        }
    }

    fun fieldsAreValid(): Boolean {
        val all = fields.all {
            val split = it.split(":")
            val field = split[0]
            val value = split[1]

            val rule = when (field) {
                "byr" -> value.toInt() in 1920..2002
                "iyr" -> value.toInt() in 2010..2020
                "eyr" -> value.toInt() in 2020..2030
                "hgt" -> {
                    val num = value.substring(0, value.length - 2).toInt()
                    if (value.endsWith("cm")) {
                        num in 150..193
                    } else {
                        num in 59..76
                    }
                }
                "hcl" -> value.matches(Regex("^#[0-9a-f]{6}$"))
                "ecl" -> listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(value)
                "pid" -> value.matches(Regex("^[0-9]{9}$"))

                else -> true
            }
//            println("$it is valid: $rule")
            rule
        }
//        println("passenger is valid: $all")
//        println()
        return all
    }
}


