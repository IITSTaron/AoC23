package com.marvin.aoc23

class Day10: Day {
    override fun handlePart1(input: String): Any {
        val map = renderMap(input)
        var steps = 0
        var (x, y) = identifyStartingCoordinates(map)
        var direction: Direction
        if(map.get(x-1, y) in listOf('-', 'L', 'F')) {
            direction = Direction.LEFT
        } else if(map.get(x, y-1) in listOf('|', 'F', '7')) {
            direction = Direction.UP
        } else if(map.get(x+1, y) in listOf('-', 'J', '7')) {
            direction = Direction.RIGHT
        } else if(map.get(x, y+1) in listOf('|', 'L', 'J')) {
            direction = Direction.DOWN
        } else {
            error("No passable pipeline could be found around 'S'")
        }
        steps++
        while(true) {
            when(direction) {
                Direction.LEFT -> {
                    x -= 1
                    map.get(x, y).let {
                        if(it == 'F') {
                            direction = Direction.DOWN
                        } else if (it == 'L') {
                            direction = Direction.UP
                        }
                    }
                }
                Direction.RIGHT -> {
                    x += 1
                    map.get(x, y).let {
                        if(it == '7') {
                            direction = Direction.DOWN
                        } else if (it == 'J') {
                            direction = Direction.UP
                        }
                    }
                }
                Direction.UP -> {
                    y -= 1
                    map.get(x, y).let {
                        if(it == 'F') {
                            direction = Direction.RIGHT
                        } else if (it == '7') {
                            direction = Direction.LEFT
                        }
                    }
                }
                Direction.DOWN -> {
                    y += 1
                    map.get(x, y).let {
                        if(it == 'L') {
                            direction = Direction.RIGHT
                        } else if (it == 'J') {
                            direction = Direction.LEFT
                        }
                    }
                }
            }
            if(map.get(x, y) == 'S') {
                return steps
            } else {
                steps ++
            }
        }
    }

    override fun handlePart2(input: String): Any {
        TODO("Not yet implemented")
    }

    enum class Direction { UP, DOWN, LEFT, RIGHT }

    private fun renderMap(input: String): MutableList<MutableList<Char>> {
        return input.split("\n").filter { it.isNotBlank() }.map {row ->
            row.toCharArray().toMutableList()
        }.toMutableList()
    }

    private fun identifyStartingCoordinates(map: MutableList<MutableList<Char>>): Pair<Int, Int> {
        map.forEachIndexed { row, chars ->
            chars.indexOf('S').let { if(it > -1) return it to row }
        }
        error("S was not found in the given map!")
    }

    private fun MutableList<MutableList<Char>>.get(x: Int, y: Int) = runCatching { this[y][x] }.getOrElse { '.' }
}