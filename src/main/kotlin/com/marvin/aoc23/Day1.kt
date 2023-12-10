package com.marvin.aoc23

class Day1: Day {
    override fun handlePart1(input: String): Int = input.split("\n").mapNotNull { line ->
        line.filter { char -> char.isDigit() }.let {
            if (it.isBlank()) null else "${it.first()}${it.last()}".toInt()
        }
    }.sum()

    override fun handlePart2(input: String): Int = input.split("\n")
            .filter { it.isNotBlank() }
            .map { line ->
        val firstOccurrence = line.findNumericOccurrence(false)
        val lastOccurrence = line.findNumericOccurrence(true)
        return@map "${firstOccurrence}${lastOccurrence}".toInt()
    }.sum()


    private fun String.findNumericOccurrence(reverse: Boolean = false): String {
        for (i in 0..length) {
            val snippet = if (reverse) substring(indices.last - i, indices.last + 1) else substring(indices.first, i)
            numberStringRepresentations.forEach { (stringRep, numericRep) ->
                if (snippet.contains(stringRep) || snippet.contains(numericRep.toString())) {
                    return numericRep.toString()
                }
            }
        }
        throw IllegalArgumentException("No numeric representation in input $this")
    }

    private val numberStringRepresentations = mapOf(
            "one" to 1,
            "two" to 2,
            "three" to 3,
            "four" to 4,
            "five" to 5,
            "six" to 6,
            "seven" to 7,
            "eight" to 8,
            "nine" to 9,
    )
}