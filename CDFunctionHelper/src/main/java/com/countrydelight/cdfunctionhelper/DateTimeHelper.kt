package com.countrydelight.cdfunctionhelper

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * Executes a given operation safely, handling potential exceptions.
 *
 * This function takes a lambda expression as input, representing the operation to be performed.
 * It attempts to execute the operation and returns its result.  If any exception occurs during
 * the execution of the operation, the exception is printed to the standard error stream,
 * and `null` is returned.
 *
 * @param operation The lambda expression representing the operation to be performed.  The lambda
 *                  should return a value of type `T`.
 * @return The result of the operation, or `null` if an exception occurred.
 */
internal fun <T> safeOperation(operation: () -> T): T? {
    return try {
        operation()
    } catch (exception: Exception) {
        exception.printStackTrace()
        null
    }
}

/**
 * Returns the current time in milliseconds.
 */
fun getCurrentMillis() = System.currentTimeMillis()

/**
 * Gets the current date and time formatted according to the specified format and locale.
 *
 * @param format The date and time format string.
 * @param locale The locale to use for formatting. Defaults to the device's default locale.
 * @return The formatted date and time as a String, or null if an error occurs during formatting.
 */
fun getCurrentFormattedDateTime(format: String, locale: Locale = Locale.getDefault()): String? {
    return safeOperation {
        val dateFormat = SimpleDateFormat(format, locale)
        val date = Date()
        dateFormat.format(date)
    }
}

/**
 * Formats a given timestamp in milliseconds into a date and time string.
 *
 * @param millis The timestamp in milliseconds. Can be null.
 * @param format The date and time format string to use.  See {@link SimpleDateFormat} for details.
 * @param locale The locale to use for formatting. Defaults to the device's default locale.
 * @return The formatted date and time string, or null if the input milliseconds are null.
 *         Note that exceptions during formatting are caught and return null.
 */
fun getFormattedDateTime(
    millis: Long?,
    format: String,
    locale: Locale = Locale.getDefault()
): String? {
    return millis?.let {
        safeOperation {
            val dateFormat = SimpleDateFormat(format, locale)
            val date = Date(it)
            dateFormat.format(date)
        }
    }
}

/**
 * Formats a date string from one format to another.
 *
 * @param date The date string to format.  Can be null
 * @param inputFormat The format of the input date string.  Must be a valid SimpleDateFormat pattern.
 * @param outputFormat The desired format of the output date string. Must be a valid SimpleDateFormat pattern.
 * @param locale The locale to use for date formatting. Defaults to the default locale.
 * @return The formatted date string, or null if the input date string is null or if an error occurs during parsing or formatting.
 */
fun getFormattedDateTime(
    date: String?,
    inputFormat: String,
    outputFormat: String,
    locale: Locale = Locale.getDefault()
): String? {
    return date?.let {
        safeOperation {
            val inputDateFormat = SimpleDateFormat(inputFormat, locale)
            val outputDateFormat = SimpleDateFormat(outputFormat, locale)
            val inputDate = inputDateFormat.parse(it)
            outputDateFormat.format(inputDate)
        }
    }
}

/**
 * Checks if two given dates fall on the same day.
 *
 * @param date1 The first date to compare. Can be null.
 * @param date2 The second date to compare. Can be null.
 * @return True if both dates are not null and represent the same day (year, month, and day), false otherwise.
 */
fun isSameDay(date1: Date?, date2: Date?): Boolean {
    return if (date1 == null || date2 == null) {
        false
    } else {
        safeOperation {
            val calendar1 = Calendar.getInstance().apply { time = date1 }
            val calendar2 = Calendar.getInstance().apply { time = date2 }
            calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                    calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
        } == true
    }
}

/**
 * Checks if the given date is the same day as the current date.
 *
 * @param date The date to compare against the current date.  Can be null.
 * @return True if the given date is the same day as the current date, false otherwise.
 */
fun isSameDay(date: Date?): Boolean {
    return isSameDay(Date(), date)
}

/**
 * Checks if date2 is the day after date1.
 *
 * @param date1 The first date.
 * @param date2 The second date.
 * @return True if date2 is the day after date1, false otherwise.  Returns false if either date is null.
 */
fun isNextDay(date1: Date?, date2: Date?): Boolean {
    return if (date1 == null || date2 == null) {
        false
    } else {
        safeOperation {
            val calendar1 = Calendar.getInstance().apply { time = date1 }
            val calendar2 = Calendar.getInstance().apply { time = date2 }
            calendar1.add(Calendar.DAY_OF_MONTH, 1)
            calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                    calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
        } == true
    }
}

/**
 * Checks if the given date is the day after the current date.
 *
 * @param date The date to check.  If null, returns false.
 * @return True if the given date is the day after the current date, false otherwise.
 */
fun isNextDay(date: Date?): Boolean {
    return isNextDay(Date(), date)
}

/**
 * Checks if date2 is the day before date1.
 *
 * @param date1 The first date to compare.
 * @param date2 The second date to compare.
 * @return True if date2 is exactly one day before date1, false otherwise.
 */
fun isPreviousDay(date1: Date?, date2: Date?): Boolean {
    return if (date1 == null || date2 == null) {
        false
    } else {
        safeOperation {
            val calendar1 = Calendar.getInstance().apply { time = date1 }
            val calendar2 = Calendar.getInstance().apply { time = date2 }
            calendar1.add(Calendar.DAY_OF_MONTH, -1)
            calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                    calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH) &&
                    calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH)
        } == true
    }
}

/**
 * Checks if the given date is the day before the current date.
 *
 * @param date The date to check.  If null, returns false.
 * @return True if the given date is the day before the current date, false otherwise.
 */
fun isPreviousDay(date: Date?): Boolean {
    return isPreviousDay(Date(), date)
}

/**
 * Converts this string to a [Date] object using the specified format and locale.
 *
 * @param format The date format string to use.  See [SimpleDateFormat] for formatting details.
 * @param locale The locale to use for parsing. Defaults to the default locale.
 * @return A [Date] object representing the parsed date, or `null` if parsing fails.
 */
fun String.toDate(format: String, locale: Locale = Locale.getDefault()): Date? {
    return safeOperation {
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.parse(this)
    }
}

/**
 * Formats this Date object into a String using the specified format and locale.
 *
 * @param format The date format string as specified by [SimpleDateFormat].  For example, "yyyy-MM-dd HH:mm:ss".
 * @param locale The locale to use for formatting. Defaults to the default locale.
 * @return The formatted date string, or null if an exception occurred during formatting.
 */
fun Date.toFormattedString(format: String, locale: Locale = Locale.getDefault()): String? {
    return safeOperation {
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.format(this)
    }
}

/**
 * Returns the day of the week for this Date.
 *
 * The returned value is an integer representing the day of the week,
 * where 1 represents Sunday, 2 represents Monday, and so on, up to 7 for Saturday.
 *
 * @return An integer representing the day of the week (1 for Sunday, 2 for Monday, ..., 7 for Saturday).
 */
fun Date.dayOfWeek(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_WEEK)
}

/**
 * Gets the current day of the week.
 *
 * This method uses the system's current date and time to determine the day of the week.
 * where 1 represents Sunday, 2 represents Monday, and so on, up to 7 for Saturday.
 *
 *  @return An integer representing the day of the week (1 for Sunday, 2 for Monday, ..., 7 for Saturday).
 */
fun dayOfWeek(): Int {
    return Date().dayOfWeek()
}

/**
 * Returns the day of the month for this Date object, considering the specified locale.
 *
 * @param locale The locale to use for determining the day of the month. Defaults to the device's default locale.
 * @return The day of the month (1-31).
 */
fun Date.dayOfMonth(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

/**
 * Returns the day of the month for the current date, using the specified locale.
 *
 * @param locale The locale to use for date formatting. If not specified, the default locale is used.
 * @return The day of the month (1-31).
 */
fun dayOfMonth(locale: Locale = Locale.getDefault()): Int {
    return Date().dayOfMonth(locale)
}

/**
 * Gets the month of the year for this date, using the specified locale.
 *
 * Months are 1-indexed (January = 1, February = 2, ..., December = 12).
 *
 * @param locale The locale to use for determining the month. Defaults to the default locale.
 * @return The month of the year (1-12).
 */
fun Date.monthOfYear(locale: Locale = Locale.getDefault()): Int {
    val calendar = Calendar.getInstance(locale)
    calendar.time = this
    return (calendar.get(Calendar.MONTH) + 1)
}

/**
 * Returns the month of the year (1-12) based on the provided locale.
 *
 * Uses the current date and time to determine the month.  If no locale is specified,
 * the default locale is used.
 *
 * @param locale The locale to use for month formatting. Defaults to the default locale.
 * @return The month of the year (1-based index, where 1 is January and 12 is December).
 */
fun monthOfYear(locale: Locale = Locale.getDefault()): Int {
    return Date().monthOfYear(locale)
}

/**
 * Returns the year represented by this Date object.
 *
 * @return The year of this Date object.
 */
fun Date.year(): Int {
    val calendar = Calendar.getInstance()
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

/**
 * Returns the current year.
 *
 * This method uses the system's current date and time to determine the current year.
 *
 * @return The current year as an integer.
 */
fun year(): Int {
    return Date().year()
}



