package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day11Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\MarvinTaron\\IdeaProjects\\aoc23\\src\\main\\resources\\day11.txt")
        println(Day11().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\MarvinTaron\\IdeaProjects\\aoc23\\src\\main\\resources\\day11.txt")
        println(Day11().handlePart2(input))
    }

    @Test
    fun testPart1() {
        val input = FileUtil.readFileContent("C:\\Users\\MarvinTaron\\IdeaProjects\\aoc23\\src\\main\\resources\\day11Test.txt")
        Assertions.assertEquals(374L, Day11().handlePart1(input))
    }

    @Test
    fun testPart2() {
        val input = FileUtil.readFileContent("C:\\Users\\MarvinTaron\\IdeaProjects\\aoc23\\src\\main\\resources\\day11Test.txt")
        Assertions.assertEquals(8410L, Day11().handlePart2(input))
    }
}
