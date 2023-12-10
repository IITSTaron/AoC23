package com.marvin.aoc23

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AocApplication

fun main(args: Array<String>) {
    runApplication<AocApplication>(*args) {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day8.txt")
        println(Day8().handlePart2(input))
    }
}