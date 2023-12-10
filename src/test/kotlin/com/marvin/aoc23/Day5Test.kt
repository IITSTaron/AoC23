package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day5Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day5.txt")
        println(Day5().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day5.txt")
        println(Day5().handlePart2(input))
    }

    @Test
    fun testPart1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day5Test.txt")
        Assertions.assertEquals(35L, Day5().handlePart1(input))
    }

    @Test
    fun testPart2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day5Test.txt")
        Assertions.assertEquals(46L, Day5().handlePart2(input))
    }
}