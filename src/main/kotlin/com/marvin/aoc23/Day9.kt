package com.marvin.aoc23

import kotlin.math.abs

class Day9 : Day {
    override fun handlePart1(input: String): Any {
        val histories = calculateHistories(input)

        //Flip and calculate missing numbers
        val flippedHistories = histories.map { it.reversed() }
        flippedHistories.forEach { history ->
            history.forEachIndexed { index, it ->
                it.add(if (index == 0) 0 else it.last() + history[index - 1].last())
            }
        }

        return flippedHistories.map { it.last() }.sumOf { it.last() }
    }

    override fun handlePart2(input: String): Any {
        val histories = calculateHistories(input)

        //Flip and calculate missing numbers
        val flippedHistories = histories.map { it.reversed() }
        flippedHistories.forEach { history ->
            history.forEachIndexed { index, it ->
                it.add(0, if (index == 0) 0 else it.first() - history[index - 1].first())
            }
        }

        return flippedHistories.map { it.last() }.sumOf { it.first() }
    }

    private fun calculateHistories(input: String): List<MutableList<MutableList<Long>>> {
        val histories: List<MutableList<MutableList<Long>>> =
                input.split("\n")
                        .filter { it.isNotBlank() }
                        .map { mutableListOf(it.split(" ").map { it.toLong() }.toMutableList()) }
        //Calculate trees x x x -> 0
        histories.forEach { history ->
            while (history.last().distinct() != listOf(0L)) {
                history.last().mapIndexed { index, _ ->
                    history.last().getOrNull(index + 1)?.let { it - history.last().get(index) }
                }.let { history.add(it.filterNotNull().toMutableList()) }
            }
        }
        return histories
    }

}