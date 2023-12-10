package com.marvin.aoc23

import com.marvin.aoc23.ListUtil.fifth
import com.marvin.aoc23.ListUtil.fourth
import com.marvin.aoc23.ListUtil.second
import com.marvin.aoc23.ListUtil.third

class Day7: Day {
    override fun handlePart1(input: String): Any {
        val hands = parseHands(input).sortedWith(compareBy(
                { it.handRanking() },
                { it.cards.first().strength },
                { it.cards.second().strength },
                { it.cards.third().strength },
                { it.cards.fourth().strength },
                { it.cards.fifth().strength },
        ))

        val rankedHands = hands.mapIndexed { index, hand -> index + 1 to hand }

        return rankedHands.sumOf { (rank, hand) -> rank * hand.bid }
    }

    override fun handlePart2(input: String): Any {
        val hands = parseHands(input).sortedWith(compareBy(
                { it.handRanking() },
                { it.cards.first().strength },
                { it.cards.second().strength },
                { it.cards.third().strength },
                { it.cards.fourth().strength },
                { it.cards.fifth().strength },
        ))

        val rankedHands = hands.mapIndexed { index, hand -> index + 1 to hand }

        return rankedHands.sumOf { (rank, hand) -> rank * hand.bid }
    }

    private fun parseHands(input: String): List<Hand> = input.split("\n")
            .filter { it.isNotBlank() }
            .map { line ->
                line.split(" ").let {
                    Hand(
                        cards = it.first().map { Card.fromName(it.toString()) },
                        bid = it.second().toInt()
                    )
                }
    }

    class Hand (
        val cards: List<Card> = listOf(),
        val bid: Int,
    ) {
        fun isFiveOfAKind(): Boolean {
            val cardPilesExJoker = cards.groupingBy { it }.eachCount().filter { it.key !== Card.J  }
            return ((cardPilesExJoker.maxOfOrNull { it.value } ?: 0) + cards.count { it == Card.J }) == 5
        }
        fun isFourOfAKind(): Boolean {
            val cardPilesExJoker = cards.groupingBy { it }.eachCount().filter { it.key !== Card.J  }
            return ((cardPilesExJoker.maxOfOrNull { it.value } ?: 0) + cards.count { it == Card.J }) == 4
        }
        fun isFullHouse(): Boolean {
            val numberOfJokers = cards.count { it === Card.J }
            val cardPilesExJoker = cards.groupingBy { it }.eachCount().filter { it.key !== Card.J  }

            return (numberOfJokers == 2 && cardPilesExJoker.filter { it.value == 2 }.size == 1) || //JJQQ2
                    (numberOfJokers == 1 && cardPilesExJoker.filter { it.value == 2 }.size == 2) || //QQJKK
                    (numberOfJokers == 0 && cards.groupBy { it.name }.let { it.any { it.value.size == 3 } && it.any { it.value.size == 2 } }) //QQQKK
        }
        fun isThreeOfAKind(): Boolean {
            val cardPilesExJoker = cards.groupingBy { it }.eachCount().filter { it.key !== Card.J  }
            return ((cardPilesExJoker.maxOfOrNull { it.value } ?: 0) + cards.count { it == Card.J }) == 3
        }
        fun isTwoPair() = cards.groupBy { it.name }.let { it.count { it.value.size == 2 } == 2 && it.count { it.value.size == 1 } == 1 } && cards.none { it == Card.J }

        fun isPair() = cards.groupBy { it.name }.let { it.count { it.value.size == 2 } == 1 && it.count { it.value.size == 1 } == 3 } || cards.any { it === Card.J }
        fun isHighCard() = cards.distinct().size == 5 && cards.none { it === Card.J }

        fun handRanking() = when {
            isFiveOfAKind() -> 7
            isFourOfAKind() -> 6
            isFullHouse() -> 5
            isThreeOfAKind() -> 4
            isTwoPair() -> 3
            isPair() -> 2
            isHighCard() -> 1
            else -> error("Unexpected hand ${cards}")
        }
    }

    enum class Card(val strength: Int) {
        J(1),
        `2`(2),
        `3`(3),
        `4`(4),
        `5`(5),
        `6`(6),
        `7`(7),
        `8`(8),
        `9`(9),
        T(10),
        Q(11),
        K(12),
        A(13);

        companion object {
            fun fromName(name: String) = entries.find { it.name == name }
                    ?: throw IllegalArgumentException("Unknown Card: $name")
        }
    }
}