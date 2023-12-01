package com.matsemann.adventofcode2022

import java.util.TreeMap
import kotlin.math.*


class Cached<I1, O>(val func: Cached<I1, O>.(I1) -> O) {
    private val cache = mutableMapOf<I1, O>()

    operator fun invoke(i: I1): O {
        return if (i in cache) {
            cache[i]!!
        } else {
            this.func(i).also {
                cache[i] = it
            }
        }
    }
}

class Cached2<I1, I2, O>(val func: Cached2<I1, I2, O>.(I1, I2) -> O) {
    private val cache = mutableMapOf<Pair<I1, I2>, O>()

    operator fun invoke(i1: I1, i2: I2): O {
        val key = i1 to i2
        return if (key in cache) {
            cache[key]!!
        } else {
            this.func(i1, i2).also {
                cache[key] = it
            }
        }
    }
}










operator fun <E> Int.times(list: List<E>): List<List<E>> {
    return List(this) { list }
}

@JvmName("timesMutable")
operator fun <E> Int.times(list: MutableList<E>) =
    MutableList(this) { list.toMutableList() }



/*
Or a functional variant I wrote lol
fun <E> List<List<E>>.transpose2(): List<List<E>> {
    val size = this.getOrNull(0)?.size ?: 0
    return this.fold(size * listOf()) { es, acc ->
        es.zip(acc) { el1, el2 -> el1 + el2}
    }
}

 */









/**
 * A map where computeIfAbsent is called on each getter
 * Items sorted by key, so safe to use as a sparse list
 */
class DefaultMap<K, V>(val map: MutableMap<K, V> = TreeMap(), val default: (K) -> V) : MutableMap<K, V> by map {

    //    constructor(default: () -> V) : this(mutableMapOf<K, V>(), default)
    override operator fun get(key: K): V {
        return map.computeIfAbsent(key) {
            default(key)
        }
    }
}

/**
 * A map where computeIfAbsent is called on each getter
 *
 */
class DefaultMap2<K, V>(val map: MutableMap<K, V> = mutableMapOf(), val default: (K) -> V) : MutableMap<K, V> by map {

    override operator fun get(key: K): V {
        return map.computeIfAbsent(key) {
            default(key)
        }
    }
}



