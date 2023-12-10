package com.marvin.aoc23

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Day7Test {
    @Test
    fun producePart1Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day7.txt")
        println(Day7().handlePart1(input))
    }

    @Test
    fun producePart2Result() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day7.txt")
        println(Day7().handlePart2(input))
    }

    @Test
    fun testPart1() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day7Test.txt")
        Assertions.assertEquals(6440, Day7().handlePart1(input))
    }

    @Test
    fun testPart2() {
        val input = FileUtil.readFileContent("C:\\Users\\Marvin\\IdeaProjects\\aoc23\\src\\main\\resources\\day7Test.txt")
        Assertions.assertEquals(5905, Day7().handlePart2(input))
    }

    @Test
    fun `Hand - isFiveOfAKind`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.A,
        )).isFiveOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
        )).isFiveOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
        )).isFiveOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.J,
        )).isFiveOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.J,
        )).isFiveOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.J,
        )).isFiveOfAKind())
        Assertions.assertFalse(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.K,
                Day7.Card.A,
        )).isFiveOfAKind())
        Assertions.assertFalse(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.K,
        )).isFiveOfAKind())
    }

    @Test
    fun `Hand - isFourOfAKind`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.Q,
        )).isFourOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.K,
                Day7.Card.Q,
        )).isFourOfAKind())
        Assertions.assertFalse(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.T,
                Day7.Card.K,
        )).isFourOfAKind())
    }

    @Test
    fun `Hand - isThreeOfAKind`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.K,
                Day7.Card.Q,
        )).isThreeOfAKind())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.K,
                Day7.Card.Q,
        )).isThreeOfAKind())
    }

    @Test
    fun `Hand - isTwoPair`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.K,
                Day7.Card.T,
                Day7.Card.K,
        )).isTwoPair())
        Assertions.assertFalse(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.T,
                Day7.Card.K,
        )).isTwoPair())
    }

    @Test
    fun `Hand - isPair`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.T,
                Day7.Card.K,
                Day7.Card.J,
                Day7.Card.Q,
        )).isPair())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.T,
                Day7.Card.K,
                Day7.Card.T,
                Day7.Card.Q,
        )).isPair())
    }

    @Test
    fun `Hand - isHighCard()`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.`2`,
                Day7.Card.K,
                Day7.Card.T,
                Day7.Card.Q,
        )).isHighCard())
        Assertions.assertFalse(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.`2`,
                Day7.Card.K,
                Day7.Card.J,
                Day7.Card.Q,
        )).isHighCard())
    }

    @Test
    fun `Hand - isFullHouse`() {
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.A,
                Day7.Card.Q,
                Day7.Card.Q,
        )).isFullHouse())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.A,
                Day7.Card.Q,
                Day7.Card.Q,
        )).isFullHouse())
        Assertions.assertTrue(Day7.Hand(bid = 1, cards = listOf(
                Day7.Card.A,
                Day7.Card.J,
                Day7.Card.J,
                Day7.Card.Q,
                Day7.Card.Q,
        )).isFullHouse())
    }
}