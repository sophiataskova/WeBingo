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

fun generateRandomBingoCard() : Set<Int> {
    var resultSet = LinkedHashSet<Int>()
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
            (selectedBingoNumbers.contains(bingoCard.elementAt(it + 1))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it + 2))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it + 3))) &&
            (selectedBingoNumbers.contains(bingoCard.elementAt(it + 4))) }
}

/**
 * Theoretically, this could run infinitely
 */
fun chooseKUniqueElementsFromRange(k:Int, r: IntRange) : Set<Int> {
    val result = LinkedHashSet<Int>()

    val random = SecureRandom()

    while (result.size.compareTo(k) < 0) {
        result.add((r.elementAt(random.nextInt(r.last - r.first))))
    }
    return result
}

fun getBingoNumToShow() : Int {
    val divideBy: Long = 4000
    return (((System.currentTimeMillis()/divideBy * 3) + (System.currentTimeMillis()-7)) % 75).toInt()
}

fun numberToBingoNumber(number: Int): String {
    var bingoNumber = ""
    if (number in 1..15) {
        bingoNumber = "B" + number
    } else if (number in 16..30) {
        bingoNumber = "I" + number
    } else if (number in 31..45) {
        bingoNumber = "N" + number
    } else if (number in 46..60) {
        bingoNumber = "G" + number
    } else if (number in 60..75) {
        bingoNumber = "O" + number
    }
    return bingoNumber
}
