package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day1Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day1.txt")
        println(Day1().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day1.txt")
        println(Day1().handlePart2(input))
    }

    @Test
    fun test() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day1Test.txt")
        Assertions.assertEquals(281, Day1().handlePart2(input))
    }
}