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
    return replace(Regex(pattern = "(<.+?>)|(&((\\w+)|(#\\w+));)"), "").replace(Regex(pattern = " {2,}"), " ")
}