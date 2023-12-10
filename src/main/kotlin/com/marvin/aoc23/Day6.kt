package com.marvin.aoc23

import com.marvin.aoc23.ListUtil.second
import java.util.concurrent.LinkedBlockingQueue
import kotlin.concurrent.thread

class Day6 : Day {
    override fun handlePart1(input: String): Any =
        parseRacesPart1(input)
            .map { it.getWinningVelocities().size }
            .reduce { acc, i -> acc * i }

    override fun handlePart2(input: String): Any =
        parseRacePart2(input).getNoOfWinningVelocitiesThreaded()

    private fun parseRacesPart1(input: String): List<Race> {
        val numericRegex = Regex("^[0-9]*\$")
        input.split("\n").let {
            val times = it.first().split(" ").filter { it.isNotBlank() && it.matches(numericRegex) }.map { it.toLong() }
            val distances = it.second().split(" ").filter { it.isNotBlank() && it.matches(numericRegex) }.map { it.toLong() }
            val races = mutableListOf<Race>()
            for (i in times.indices) {
                races.add(Race(times[i], distances[i]))
            }
            return races
        }
    }

    private fun parseRacePart2(input: String): Race {
        val numericRegex = Regex("^[0-9]*\$")
        input.split("\n").let {
            val time = it.first().split(" ").filter { it.isNotBlank() && it.matches(numericRegex) }.joinToString("").toLong()
            val distance = it.second().split(" ").filter { it.isNotBlank() && it.matches(numericRegex) }.joinToString("").toLong()
            return Race(time, distance)
        }
    }

    class Race(
            val time: Long,
            val distance: Long,
    ) {
        fun getWinningVelocities(): List<Long> = (1.. time).filterIndexed { index, chargeTime ->
            println("$index / $time")
            ((time - chargeTime) * chargeTime) > distance
        }

        fun getNoOfWinningVelocitiesThreaded(): Long {
            val list = mutableListOf<Long>()
            (1..time).chunked(1000000).mapIndexed { index, times ->
                thread {
                    list.add(times.count { chargeTime -> ((time - chargeTime) * chargeTime) > distance }.toLong())
                }
            }.forEach { it.join() }
            return list.sum()
        }
    }
}