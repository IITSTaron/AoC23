package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day10Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\Day10.txt")
        println(Day10().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\Day10.txt")
        println(Day10().handlePart2(input))
    }

    @Test
    fun test1Part1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day10Test1Part1.txt")
        Assertions.assertEquals(4, Day10().handlePart1(input))
    }

    @Test
    fun test2Part1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day10Test2Part1.txt")
        Assertions.assertEquals(8, Day10().handlePart1(input))
    }

    @Test
    fun test1Part2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day10Test1Part2.txt")
        Assertions.assertEquals(4, Day10().handlePart2(input))
    }

    @Test
    fun test2Part2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day10Test2Part2.txt")
        Assertions.assertEquals(1, Day10().handlePart2(input))
    }
}