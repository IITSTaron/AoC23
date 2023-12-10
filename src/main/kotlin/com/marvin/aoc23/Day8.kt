package com.marvin.aoc23

import com.marvin.aoc23.ListUtil.second

class Day8 : Day {
    override fun handlePart1(input: String): Any {
        val movements = input.split("\n").first()
        val map = renderMap(input)

        var totalMovements = 0
        var currentPosition = "AAA"
        while(currentPosition != "ZZZ") {
            val movement = movements[totalMovements % movements.length]
            currentPosition = map[currentPosition]?.let { if(movement == 'L') it.first else it.second } ?: error("Unknown position: ${map[currentPosition]}")
            totalMovements++
        }
        return totalMovements
    }

    private fun renderMap(input: String): HashMap<String, Pair<String, String>> {
        val map = HashMap<String, Pair<String, String>>()
        input.split("\n").filter { it.contains("=") }.forEach {
            it.replace("(", "").replace(")", "").replace(" ", "").let { cleanedRow ->
                val node = cleanedRow.split("=").first()
                val directions = cleanedRow.split("=").second().let { it.split(",").let { it.first() to it.second() } }
                map[node] = directions
            }
        }
        return map
    }

    override fun handlePart2(input: String): Any {
        val movements = input.split("\n").first()
        val map = renderMap(input)

        val startPositions = map.keys.filter { it.endsWith("A") }
        val neededMovements = startPositions.map {
            var currentPosition = it
            var moves = 0L
            while(!currentPosition.endsWith("Z")) {
                val movement = movements[(moves % movements.length).toInt()]
                currentPosition = map[currentPosition]?.let { if(movement == 'L') it.first else it.second } ?: error("Unknown position: ${map[currentPosition]}")
                moves++
            }
            return@map moves
        }
        return neededMovements.reduce { acc, i -> lcm(acc, i) }
    }

    private fun lcm(a: Long, b: Long): Long {
        var ma = a
        var mb = b
        var remainder: Long

        while (mb != 0L) {
            remainder = ma % mb
            ma = mb
            mb = remainder
        }

        return a * b / ma
    }
}