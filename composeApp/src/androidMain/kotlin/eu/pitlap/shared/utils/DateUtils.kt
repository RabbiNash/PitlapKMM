package eu.pitlap.shared.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

object DateUtils {

    private fun parseISODate(dateString: String): Date? {
        return try {
            val formatter = DateTimeFormatter.ISO_DATE_TIME
            val instant = Instant.from(formatter.parse(dateString))
            Date.from(instant)
        } catch (e: Exception) {
            null
        }
    }

    fun getHumanisedDate(dateString: String?): String? {
        return dateString?.let {
            parseISODate(it)?.let {
                SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.getDefault()).format(it)
            }
        }
    }

    fun getDayOnly(dateString: String): String? {
        return parseISODate(dateString)?.let {
            SimpleDateFormat("dd\nMMM", Locale.getDefault()).format(it)
        }
    }

    fun getDayAndTimeWithNewLine(dateString: String): String? {
        return parseISODate(dateString)?.let {
            SimpleDateFormat("dd MMM", Locale.getDefault()).format(it)
        }
    }

    fun isPastDate(sessionTime: String): Boolean {
        val currentDate = Date()
        val eventDate = getDateFromString(sessionTime)
        return eventDate?.before(currentDate) ?: false
    }

    fun getDateFromString(dateString: String): Date? = parseISODate(dateString)

    fun getHumanisedDateWithTime(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("MMMM d, yyyy h:mm a", Locale.getDefault()).format(it)
        }
    }

    fun getHumanisedShortDateWithTime(date: Date): String {
        return SimpleDateFormat("MMM d, h:mm a", Locale.getDefault()).format(date)
    }

    fun getHumanisedShortDateWithTime(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("MMM d, h:mm a", Locale.getDefault()).format(it)
        }
    }

    fun getHumanisedShortDate(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("MMM d", Locale.getDefault()).format(it)
        }
    }

    fun getHumanisedTime(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("h:mm a", Locale.getDefault()).format(it)
        }
    }

    fun getCustomFormattedDate(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("dd MMM HH:mm", Locale.getDefault()).format(it)
        }
    }

    fun getCustomFormattedDay(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("EEEE d MMMM yyyy", Locale.getDefault()).format(it)
        }
    }

    fun getCustomFormattedTime(apiDate: String): String? {
        return parseISODate(apiDate)?.let {
            SimpleDateFormat("HH:mm", Locale.getDefault()).format(it)
        }
    }

    fun getLocalTimezone(): String {
        return TimeZone.getDefault().id
    }
}
