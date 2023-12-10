package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day2Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day2.txt")
        println(Day2().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day2.txt")
        println(Day2().handlePart2(input))
    }

    @Test
    fun testPart1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day2Test.txt")
        Assertions.assertEquals(8, Day2().handlePart1(input))
    }

    @Test
    fun testPart2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day2Test.txt")
        Assertions.assertEquals(2286, Day2().handlePart2(input))
    }
}