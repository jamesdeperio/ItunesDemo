package jamesdeperio.github.itunesdemo.util

import java.util.*

object DateUtil {
    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

    fun currentDate(): Date {
        val calendar: Calendar = Calendar.getInstance()
        return calendar.time
    }

    fun getPrettyTime(timestamp: Long): String? {
        var time = timestamp
        if (time < 1000000000000L) {
          time *= 1000
        }
        val now: Long = currentDate().getTime()
        if (time > now || time <= 0) {
            return "in the future"
        }
        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> {
                "Moments ago"
            }
            diff < 2 * MINUTE_MILLIS -> {
                "A minute ago"
            }
            diff < 50 * MINUTE_MILLIS -> {
                 "${(diff / MINUTE_MILLIS)} minutes ago"
            }
            diff < 90 * MINUTE_MILLIS -> {
                "An hour ago"
            }
            diff < 24 * HOUR_MILLIS -> {
                  "${(diff / HOUR_MILLIS)} hours ago"
            }
            diff < 48 * HOUR_MILLIS -> {
                "Yesterday"
            }
            else -> {
                  "${(diff / DAY_MILLIS)} days ago"
            }
        }
    }
}