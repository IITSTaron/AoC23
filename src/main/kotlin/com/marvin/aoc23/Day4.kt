package com.marvin.aoc23

import kotlin.math.pow

class Day4 : Day {
    override fun handlePart1(input: String): Any {
        input.split("\n").filter { it.isNotBlank() }.forEach { row ->
            row.split(":").last().let { numberBlock ->
                numberBlock.split("|").let {
                    val betNumbers = it.first().split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList()
                    val winningNumbers = it.last().split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList()
                    originalScratchcards.add(LotteryCard(0, betNumbers, winningNumbers))
                }
            }
        }

        return originalScratchcards.map { lotteryCard ->
            val matches = lotteryCard.betNumbers.count { lotteryCard.winningNumbers.contains(it) }
            return@map if (matches == 0) {
                0
            } else {
                2.0.pow(matches - 1).toInt()
            }
        }.sum()
    }

    override fun handlePart2(input: String): Any {
        input.split("\n").filter { it.isNotBlank() }.forEach { row ->
            row.split(":").let { block ->
                val cardNumber = block.first().split(" ").last().toInt()
                block.last().split("|").let {
                    val betNumbers = it.first().split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList()
                    val winningNumbers = it.last().split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toMutableList()
                    originalScratchcards.add(LotteryCard(cardNumber, betNumbers, winningNumbers))
                }
            }
        }
        myScratchcards.addAll(originalScratchcards)

        var i = 0
        while(i < myScratchcards.size) {
            val currentCard = myScratchcards[i]
            val matches = currentCard.betNumbers.count { currentCard.winningNumbers.contains(it) }
            myScratchcards.addAll(originalScratchcards.filter { it.number in currentCard.number + 1 ..< currentCard.number + 1 + matches })
            i++
        }

        return myScratchcards.size
    }

    val originalScratchcards: MutableList<LotteryCard> = mutableListOf()
    val myScratchcards: MutableList<LotteryCard> = mutableListOf()

    data class LotteryCard(
        val number: Int,
        val betNumbers: MutableList<Int> = mutableListOf(),
        val winningNumbers: MutableList<Int> = mutableListOf(),
    )
}