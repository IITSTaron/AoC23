package com.marvin.aoc23

import com.marvin.aoc23.ListUtil.second
import kotlin.concurrent.thread
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties

class Day5 : Day {
    override fun handlePart1(input: String): Any {
        val almanac = renderAlmanac(input)
        val seeds = getSeeds(input)

        val locations = seeds.map {
            almanac.seedToSoil.findTarget(it).let {
                almanac.soilToFertilizer.findTarget(it).let {
                    almanac.fertilizerToWater.findTarget(it).let {
                        almanac.waterToLight.findTarget(it).let {
                            almanac.lightToTemperature.findTarget(it).let {
                                almanac.temperatureToHumidity.findTarget(it).let {
                                    almanac.humidityToLocation.findTarget(it)
                                }
                            }
                        }
                    }
                }
            }
        }
        return locations.min()
    }

    override fun handlePart2(input: String): Any {
        val almanac = renderAlmanac(input)
        val seedRanges = getSeedRangeEndpoints(input)
        val locations = mutableListOf<Long>()

        seedRanges.mapIndexed { index1, range ->
            thread {
                var minimum: Long = Long.MAX_VALUE
                range.forEachIndexed { index2, it ->
                    if (index2 % 100000 == 0) {
                        println("Map $index1: ${index2 + 1} of ${range.last - range.first} elapsed")
                    }
                    almanac.seedToSoil.findTarget(it).let {
                        almanac.soilToFertilizer.findTarget(it).let {
                            almanac.fertilizerToWater.findTarget(it).let {
                                almanac.waterToLight.findTarget(it).let {
                                    almanac.lightToTemperature.findTarget(it).let {
                                        almanac.temperatureToHumidity.findTarget(it).let {
                                            almanac.humidityToLocation.findTarget(it).let {
                                                if(it < minimum) {
                                                    minimum = it
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                locations.add(minimum)
            }
        }.forEach { it.join() }

        return locations.min()
    }

    private fun getSeeds(input: String): List<Long> = input.split("\n")
            .first { it.contains("seeds:") }
            .split(": ")
            .last()
            .split(" ")
            .map { it.toLong() }


    private fun getSeedRangeEndpoints(input: String): List<LongRange> = input.split("\n")
            .first { it.contains("seeds:") }
            .split(": ")
            .last()
            .split(" ")
            .map { it.toLong() }
            .chunked(2)
            .map { it.first()..it.first() + it.second() }


    private fun renderAlmanac(input: String): Almanac {
        val almanac = Almanac()
        val rows = input.split("\n")
        var currentList: MutableList<AlmanacEntry> = mutableListOf()
        var almanacMapProp: KMutableProperty<*> = Almanac::seedToSoil
        rows.forEach { row ->
            if (row.contains("map")) {
                almanacMapProp.setter.call(almanac, currentList)
                almanacMapProp = identifyMapInAlmanac(row)
                currentList = mutableListOf()
            } else if (row.isBlank() || row.contains("seed")) {
                //do nothing
            } else {
                val (destRangeStart, sourceRangeStart, rangeLength) = row.split(" ").map { it.toLong() }
                currentList.add(AlmanacEntry(
                        sourceRangeStart..<sourceRangeStart + rangeLength, sourceRangeStart - destRangeStart
                ))
            }
        }
        almanacMapProp.setter.call(almanac, currentList)
        return almanac
    }

    private fun identifyMapInAlmanac(rowText: String): KMutableProperty<*> =
            Almanac::class.declaredMemberProperties
                    .filterIsInstance<KMutableProperty<*>>()
                    .find { rowText.replace("-", "").contains(it.name.lowercase()) }
                    ?: throw IllegalArgumentException("No map could be determined in Almanac with soure $rowText")

    class Almanac(
            var seedToSoil: MutableList<AlmanacEntry> = mutableListOf(),
            var soilToFertilizer: MutableList<AlmanacEntry> = mutableListOf(),
            var fertilizerToWater: MutableList<AlmanacEntry> = mutableListOf(),
            var waterToLight: MutableList<AlmanacEntry> = mutableListOf(),
            var lightToTemperature: MutableList<AlmanacEntry> = mutableListOf(),
            var temperatureToHumidity: MutableList<AlmanacEntry> = mutableListOf(),
            var humidityToLocation: MutableList<AlmanacEntry> = mutableListOf(),
    )

    class AlmanacEntry(
            val sourceRange: ClosedRange<Long>,
            val offset: Long,
    )

    fun MutableList<AlmanacEntry>.findTarget(source: Long) = find { source in it.sourceRange }?.let { source - it.offset }
            ?: source

}