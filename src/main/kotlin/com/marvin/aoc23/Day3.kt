package com.marvin.aoc23

import kotlin.math.sign

class Day3 : Day {
    override fun handlePart1(input: String): Any {
        val matrix: Array<Array<Char>> = input.split("\n").filter { it.isNotBlank() }.map { it.toCharArray().toTypedArray() }.toTypedArray()
        val partNumbers: MutableList<Int> = mutableListOf()

        matrix.forEachIndexed { y, row ->
            var x = 0
            while (x < row.size) {
                if (row[x].isDigit()) {
                    val digit = row.joinToString("").substring(x).split(Regex("[^0-9]")).first()
                    if (surroundedByAnySymbol(matrix, x, x + digit.length - 1, y)) {
                        partNumbers.add(digit.toInt())
                    }
                    x += digit.length
                } else {
                    x++
                }
            }
        }

        return partNumbers.sum()
    }

    private fun Char.isSymbol() = !isDigit() && this != '.'

    private fun Array<Array<Char>>.get(x: Int, y: Int): Char = runCatching { this[y][x] }.getOrElse { '.' }

    private fun surroundedByAnySymbol(matrix: Array<Array<Char>>, startX: Int, endX: Int, y: Int): Boolean {
        //left edge
        if (matrix.get(startX - 1, y - 1).isSymbol() || matrix.get(startX - 1, y).isSymbol() || matrix.get(startX - 1, y + 1).isSymbol()) {
            return true
        }
        for (x in startX..endX) {
            if (matrix.get(x, y - 1).isSymbol() || matrix.get(x, y + 1).isSymbol()) {
                return true
            }
        }
        //right edge
        if (matrix.get(endX + 1, y - 1).isSymbol() || matrix.get(endX + 1, y).isSymbol() || matrix.get(endX + 1, y + 1).isSymbol()) {
            return true
        }
        return false
    }

    private fun checkForSurroundingGear(matrix: Array<Array<Char>>, startX: Int, endX: Int, y: Int, number: Int) {
        val coordinatesToCheck = mutableListOf<Pair<Int, Int>>()
        //left edge
        coordinatesToCheck.add(startX - 1 to y - 1)
        coordinatesToCheck.add(startX - 1 to y)
        coordinatesToCheck.add(startX - 1 to y + 1)
        //above and beyond digits
        for (x in startX..endX) {
            coordinatesToCheck.add(x to y - 1)
            coordinatesToCheck.add(x to y + 1)
        }
        //right edge
        coordinatesToCheck.add(endX + 1 to y - 1)
        coordinatesToCheck.add(endX + 1 to y)
        coordinatesToCheck.add(endX + 1 to y + 1)

        coordinatesToCheck.forEach { (x, y) ->
            if (matrix.get(x, y) == '*') {
                gearMatches.add(GearMatch(x to y, number))
            }
        }
    }

    override fun handlePart2(input: String): Any {
        val matrix: Array<Array<Char>> = input.split("\n").filter { it.isNotBlank() }.map { it.toCharArray().toTypedArray() }.toTypedArray()

        matrix.forEachIndexed { y, row ->
            var x = 0
            while (x < row.size) {
                if (row[x].isDigit()) {
                    val digit = row.joinToString("").substring(x).split(Regex("[^0-9]")).first()
                    checkForSurroundingGear(matrix, x, x + digit.length - 1, y, digit.toInt())
                    x += digit.length
                } else {
                    x++
                }
            }
        }

        val gearNumbers = gearMatches.groupBy { it.coordinates }
                .filter { (coordinates, matches) -> matches.size == 2 }
                .map { (coordinates, matches) -> matches.map { it.number }}

        return gearNumbers.sumOf { it.reduce { acc, i -> acc * i } }
    }

    fun printMatrix(matrix: Array<Array<Char>>) {
        matrix.forEach { row ->
            row.forEach {
                print(it)
            }
            println()
        }
    }

    val gearMatches: MutableList<GearMatch> = mutableListOf()

    class GearMatch(
            val coordinates: Pair<Int, Int>,
            val number: Int,
    )
}