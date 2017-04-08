package com.example.sophiataskova.webingo

import java.security.SecureRandom


val BINGO = arrayOf("B", "I", "N", "G", "O")
val RANGES = arrayOf((1..15), (16..30), (31..45), (46..60), (61..75))

fun getAllBingoEntriesInOrder() : List<String> {
    val result = ArrayList<String>()
    for (index in 0..BINGO.size - 1) {
        result.addAll(getBingoEntriesForLetter(BINGO[index], RANGES[index]))
    }
    return result
}

fun getBingoEntriesForLetter(s: String, r: IntRange) : List<String> {
    val result = ArrayList<String>()

    r.mapTo(result) { s + it }
    return result
}

fun generateRandomBingoCard() : Set<String> {
    var resultSet = LinkedHashSet<String>()
    for (range in RANGES) {
        resultSet.addAll(chooseKUniqueElementsFromRange(5, range))
    }
    return resultSet
}

/**
 * Theoretically, this could run infinitely
 */
fun chooseKUniqueElementsFromRange(k:Int, r: IntRange) : Set<String> {
    val result = LinkedHashSet<String>()

    val random = SecureRandom()

    while (result.size.compareTo(k) < 0) {
        result.add((r.elementAt(random.nextInt(r.last - r.first))).toString())
    }
    return result
}
