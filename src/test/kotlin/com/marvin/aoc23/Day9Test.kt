package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day9Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\Day9.txt")
        println(Day9().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\Day9.txt")
        println(Day9().handlePart2(input))
    }

    @Test
    fun testPart1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\Day9Test.txt")
        Assertions.assertEquals(114L, Day9().handlePart1(input))
    }

    @Test
    fun testPart2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\Day9Test.txt")
        Assertions.assertEquals(2L, Day9().handlePart2(input))
    }
}