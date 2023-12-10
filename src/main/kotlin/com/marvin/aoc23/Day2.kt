package com.marvin.aoc23

import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties

class Day2 : Day {
    override fun handlePart1(input: String): Any {
        input.split("\n")
                .filter { it.isNotBlank() }
                .forEach { renderGame(it) }

        val possibleGames = games.filter { (gameNumber, game) -> isGamePossible(game) }

        return possibleGames.keys.sum()
    }

    override fun handlePart2(input: String): Any {
        input.split("\n")
                .filter { it.isNotBlank() }
                .forEach { renderGame(it) }

        val powers = games.values.map { game ->
            game.rounds.filter { it.blue > 0 }.maxOf { it.blue } *
                    game.rounds.filter { it.green > 0 }.maxOf { it.green } *
                    game.rounds.filter { it.red > 0 }.maxOf { it.red }
        }

        return powers.sum()
    }

    private fun isGamePossible(game: Game) = game.rounds.all { it.green <= MAX_GREEN && it.blue <= MAX_BLUE && it.red <= MAX_RED }


    private fun renderGame(input: String) {
        input.split(": ").let { gameInput ->
            val gameNumber = gameInput.first().split(" ").last().toInt()
            val rounds = mutableListOf<Round>()
            gameInput.last().split("; ").forEach { roundInputs ->
                val round = Round()
                roundInputs.split(", ").forEach { roundPart ->
                    roundPart.split(" ").let { it.first() to it.last() }.let { (noOfGems, color) ->
                        round::class.memberProperties.filterIsInstance<KMutableProperty<*>>()
                                .find { it.name == color }?.setter?.call(round, noOfGems.toInt())
                    }
                }
                rounds.add(round)
            }
            games[gameNumber] = Game(rounds)
        }
    }

    private val games: HashMap<Int, Game> = HashMap()

    class Game(
            var rounds: List<Round> = listOf(),
    )

    class Round(
            var blue: Int = 0,
            var red: Int = 0,
            var green: Int = 0,
    )

    companion object {
        const val MAX_BLUE = 14
        const val MAX_GREEN = 13
        const val MAX_RED = 12
    }
}