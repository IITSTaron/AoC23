package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day6Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day6.txt")
        println(Day6().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day6.txt")
        println(Day6().handlePart2(input))
    }

    @Test
    fun testPart1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day6Test.txt")
        Assertions.assertEquals(288, Day6().handlePart1(input))
    }

    @Test
    fun testPart2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day6Test.txt")
        Assertions.assertEquals(71503L, Day6().handlePart2(input))
    }
}