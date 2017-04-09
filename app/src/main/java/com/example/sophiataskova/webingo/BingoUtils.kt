package com.example.sophiataskova.webingo

import com.example.sophiataskova.webingo.FullscreenActivity.Companion.bingoCard
import com.example.sophiataskova.webingo.FullscreenActivity.Companion.selectedBingoNumbers
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
 * Row, col, or diagonal
//
//0   1   2   3   4
//5   6   7   8   9
//10   11   12   13   14
//15   16   17   18   19
//20   21   22   23   24
 */
fun checkForBingo() : Boolean {
    (0..4).filter { selectedBingoNumbers.contains(bingoCard.elementAt(it)) &&
                    selectedBingoNumbers.contains(bingoCard.elementAt(it + 5)) &&
                    selectedBingoNumbers.contains(bingoCard.elementAt(it + 10)) &&
                    selectedBingoNumbers.contains(bingoCard.elementAt(it + 15)) &&
                    selectedBingoNumbers.contains(bingoCard.elementAt(it + 20)) }
            .forEach { return true }

    if (selectedBingoNumbers.contains(bingoCard.elementAt(0)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(6)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(12)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(18)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(24))) {
        return true
    }

    if (selectedBingoNumbers.contains(bingoCard.elementAt(4)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(8)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(12)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(16)) &&
            selectedBingoNumbers.contains(bingoCard.elementAt(20))) {
        return true
    }

    return (0..20 step 5).any { (selectedBingoNumbers.contains(bingoCard.elementAt(it))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it +1))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it +2))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it +3))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it +4))) }
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

fun getBingoNumToShow() : String {
    var bingoNumber: String = ""
    val number: Int
    val divideBy: Long = 1000
    number = (System.currentTimeMillis()/divideBy).toInt() % 75

    if (number < 15) {
        bingoNumber = "B" + number
    } else if (number <= 30) {
        bingoNumber = "I" + number
    } else if (number <= 45) {
        bingoNumber = "N" + number
    } else if (number <= 60) {
        bingoNumber = "G" + number
    } else if (number <= 75) {
        bingoNumber = "O" + number
    }

    return bingoNumber
}
