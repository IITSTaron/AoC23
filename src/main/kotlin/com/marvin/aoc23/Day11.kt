package com.marvin.aoc23

import kotlin.math.abs

class Day11: Day {
    override fun handlePart1(input: String): Any {
        val starMap: Starmap = renderStarMap(input)
        val (emptyRows, emptyCols) = calculateEmptyRowsAndCols(starMap)
        return measureDistances(starMap, emptyRows, emptyCols)
    }

    override fun handlePart2(input: String): Any {
        val starMap: Starmap = renderStarMap(input)
        val (emptyRows, emptyCols) = calculateEmptyRowsAndCols(starMap)
        return measureDistances(starMap, emptyRows, emptyCols, 1000000)
    }

    private fun renderStarMap(input: String): Starmap =
        input.split("\n").filter { it.isNotBlank() }.map { row ->
            row.trim().toCharArray().toMutableList()
        }.toMutableList()

    private fun calculateEmptyRowsAndCols(starMap: Starmap): Pair<List<Long>, List<Long>> =
        starMap.indices.filter { row -> starMap[row].all { it == '.' } }.map { it.toLong() } to
                starMap.indices.filter { row -> starMap.map { it[row] }.all { it == '.' } }.map { it.toLong() }

    private fun measureDistances(starMap: Starmap, emptyRows: List<Long>, emptyCols: List<Long>, factor: Int = 1): Long {
        val starLocations = mutableListOf<Coordinates>()
        starMap.forEachIndexed { y, row ->
            row.forEachIndexed { x, _ ->
                if(starMap[y][x] == '#') {
                    starLocations.add(x.toLong() to y.toLong())
                }
            }
        }
        return starLocations.cartesian().sumOf { (a, b) ->
            var (x1, y1) = a.first to a.second
            var (x2, y2) = b.first to b.second

            x1 += (emptyCols.count { it < x1 }) * (factor - 1)
            x2 += (emptyCols.count { it < x2 }) * (factor - 1)
            y1 += (emptyRows.count { it < y1 }) * (factor - 1)
            y2 += (emptyRows.count { it < y2 }) * (factor - 1)

            abs(x1 - x2) + abs(y1 - y2)
        }
    }

    private fun MutableList<Coordinates>.cartesian(): MutableList<Pair<Coordinates, Coordinates>> = flatMap { outer ->
        this.subList(indexOf(outer), this.size).map { inner -> outer to inner }
    }.filter { it.first != it.second }.toMutableList()
}

typealias Starmap = MutableList<MutableList<Char>>
typealias Coordinates = Pair<Long, Long>
