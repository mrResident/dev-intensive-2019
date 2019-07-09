package ru.skillbranch.devintensive.extensions

import java.lang.StringBuilder

fun String.truncate(num: Int = 16): String {
    if (num == 0) {
        return this
    }
    val result = this.trim()
    return if (result.length <= num) {
        result
    } else {
        StringBuilder(result.substring(0 until num).trim()).append("...").toString()
    }
}

fun String.stripHtml(): String {
    val matchedResults = Regex(pattern = """\d+\w+""").findAll(input = this)
    val result = StringBuilder()
    for (matchedText in matchedResults) {
        result.append(matchedText.value + " ")
    }
    return result.toString()
}