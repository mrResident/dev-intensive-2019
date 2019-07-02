package ru.skillbranch.devintensive.utils

import java.lang.StringBuilder


object Utils {

    private val translitMap = mapOf(
        "а" to "a", "б" to "b", "в" to "v",
        "г" to "g", "д" to "d", "е" to "e",
        "ё" to "e", "ж" to "zh", "з" to "z",
        "и" to "i", "й" to "i", "к" to "k",
        "л" to "l", "м" to "m", "н" to "n",
        "о" to "o", "п" to "p", "р" to "r",
        "с" to "s", "т" to "t", "у" to "u",
        "ф" to "f", "х" to "h", "ц" to "c",
        "ч" to "ch", "ш" to "sh", "щ" to "sh'",
        "ъ" to "", "ы" to "i", "ь" to "",
        "э" to "e", "ю" to "yu", "я" to "ya")

    fun parseFullName(fullName: String?): Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(" ")

        val fn = arrayListOf<String?>(null, null)

        if (parts != null) {
            var cnt = 0
            for (name in parts) {
                if (name.isNotEmpty()) {
                    fn[cnt] = name
                    cnt++
                }
                if (cnt > 1) break
            }
        }

        return fn[0] to fn[1]
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            return null
        }
        return "${if (!firstName.isNullOrBlank()) firstName.toUpperCase()[0] else ""}${if (!lastName.isNullOrBlank()) lastName.toUpperCase()[0] else ""}"
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val sb = StringBuilder()
        for (c in payload) {
            with(sb) {
                when(c) {
                    ' ' -> append(divider)
                    else -> {
                        when(translitMap[c.toString().toLowerCase()]) {
                            null -> append(c)
                            else -> {
                                if (c.isUpperCase()) {
                                    val res = translitMap[c.toString().toLowerCase()]
                                    if (res?.length!! > 1) {
                                        append(res[0].toUpperCase())
                                        for (i in 1 until res.length) {
                                            append(res[i])
                                        }
                                    } else {
                                        append(translitMap[c.toString().toLowerCase()]?.toUpperCase())
                                    }
                                } else {
                                    append(translitMap[c.toString()])
                                }
                            }
                        }
                    }
                }
            }
        }
        return sb.toString()
    }

}