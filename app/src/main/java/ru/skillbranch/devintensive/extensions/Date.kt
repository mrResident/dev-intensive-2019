package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {

    var time = this.time

    time += when(units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }

    this.time = time

    return this
}

fun Date.humanizeDiff(value: Date = Date()): String {

    var isFuture = true

    return when(val deltaTime = if (value.time > this.time) {isFuture = false; value.time - this.time} else this.time - value.time) {
        in (0 * SECOND) .. (1 * SECOND) -> "только что"
        in (1 * SECOND) .. (45 * SECOND) -> if (isFuture) "через несколько секунд" else "несколько секунд назад"
        in (45 * SECOND) .. (75 * SECOND) -> if (isFuture) "через минуту" else "минуту назад"
        in (75 * SECOND) .. (45 * MINUTE) -> {
            when(val result = (deltaTime / MINUTE).toInt()) {
                 1 -> if (isFuture) "через $result минуту" else "$result минуту назад"
                in 2 .. 4 -> if (isFuture) "через $result минуты" else "$result минуты назад"
                else -> if (isFuture) "через $result минут" else "$result минут назад"
            }
        }
        in (45 * MINUTE) .. (75 * MINUTE) -> "час назад"
        in (75 * MINUTE) .. (22 * HOUR) -> {
            when(val result = (deltaTime / HOUR).toInt()) {
                1 -> if (isFuture) "через $result час" else "$result час назад"
                in 2 .. 4 -> if (isFuture) "через $result часа" else "$result часа назад"
                else -> if (isFuture) "через $result часов" else "$result часов назад"
            }
        }
        in (2 * HOUR) .. (26 * HOUR) -> "день назад"
        in (26 * HOUR) .. (360 * DAY) -> {
            when(val result = (deltaTime / DAY).toInt()) {
                1 -> if (isFuture) "через $result день" else "$result день назад"
                in 2 .. 4 -> if (isFuture) "через $result дня" else "$result дня назад"
                else -> if (isFuture) "через $result дней" else "$result дней назад"
            }
        }
        else -> if (isFuture) "более чем через год" else "более года назад"
    }

}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}
