package com.matsemann.adventofcode2020

fun day7_1(lines: List<String>) {

    val bags = emptyMap<String, Bag>().toMutableMap()

    lines.forEach {
        val split = it.split(" contain ")
        val bagColor = split[0].removeSuffix(" bags")
        val otherBags = split[1]
        val bag = getOrCreate(bags, bagColor)

        if (otherBags == "no other bags.") {
            return@forEach
        }

        val others = otherBags
            .replace("bags", "")
            .replace("bag", "")
            .replace(".", "")
            .replace(Regex("\\d"), "")
            .split(", ")
        others.forEach {other ->
            getOrCreate(bags, other.trim()).parents.add(bag.color)
        }
    }

    val possibleParents = traverse(bags, "shiny gold")

    println("size: ${possibleParents.size - 1}")
}

fun traverse(bags: MutableMap<String, Bag>, startColor: String): MutableList<String> {
    val seen = mutableListOf(startColor)
    val queue = mutableListOf(startColor)

    while (queue.isNotEmpty()) {
        val bagColor = queue.removeFirst()
        val bag = bags[bagColor]!!

        bag.parents.forEach { parent ->
            if (!seen.contains(parent)) {
                seen.add(parent)
                queue.add(parent)
            }
        }
    }
    return seen
}


fun day7_2(lines: List<String>) {

    val bags = emptyMap<String, Bag>().toMutableMap()

    lines.forEach {
        val split = it.split(" contain ")
        val bagColor = split[0].removeSuffix(" bags")
        val otherBags = split[1]
        val bag = getOrCreate(bags, bagColor)

        if (otherBags == "no other bags.") {
            return@forEach
        }

        val others = otherBags
            .replace("bags", "")
            .replace("bag", "")
            .replace(".", "")
            .split(", ")
        others.forEach {other ->
            val count = Regex("\\d+").find(other)!!.value.toInt()
            val color = other.replace(Regex("\\d"), "").trim()
            val otherBag = getOrCreate(bags, color)
            otherBag.parents.add(bag.color)
            bag.children.add(Pair(count, color))
        }
    }

    val children = childBags(bags, "shiny gold") - 1

    println("children: $children")

}

fun childBags(bags: MutableMap<String, Bag>, bagColor: String) : Int {
    val bag = bags[bagColor]!!

    return if (bag.children.isEmpty()) {
        1
    } else {
        1 + bag.children.sumBy { child ->
            child.first * childBags(bags, child.second)
        }
    }
}

fun getOrCreate(bags: MutableMap<String, Bag>, color: String): Bag {
    return if (bags.containsKey(color))
        bags[color]!!
    else {
        val bag = Bag(color, mutableSetOf(), mutableSetOf())
        bags[color] = bag
        bag
    }
}

data class Bag(val color: String, val parents: MutableSet<String>, val children: MutableSet<Pair<Int, String>>)