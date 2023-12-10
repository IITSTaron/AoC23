package com.marvin.aoc23

import java.io.File

object FileUtil {
    fun readFileContent(path: String): String = File(path).readText()
}