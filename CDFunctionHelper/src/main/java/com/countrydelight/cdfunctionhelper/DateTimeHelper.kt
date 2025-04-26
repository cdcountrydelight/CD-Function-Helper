package com.countrydelight.cdfunctionhelper

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


/**
 * Returns the current time in milliseconds.
 */
fun getCurrentTimeInMillis() = System.currentTimeMillis()

/**
 * Gets the current time in seconds.
 *
 * @return The current time in seconds as a Long.
 */
fun getCurrentTimeInSeconds(): Long = System.currentTimeMillis() / 1000

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
            if (inputDate == null) {
                null
            } else {
                outputDateFormat.format(inputDate)
            }
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
fun Date.getDayOfWeek(): Int {
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
fun getCurrentDayOfWeek(): Int {
    return Date().getDayOfWeek()
}

/**
 * Gets the name of the day of the week for this Date object.
 *
 * @param format The date format pattern string.  Defaults to "EEEE" (full day name).  See [SimpleDateFormat] for format options.
 * @param locale The locale to use for formatting the day name. Defaults to the device's default locale.
 * @return The name of the day of the week as a String, or null if an error occurs.
 */
fun Date.getDayOfWeekName(format: String = "EEEE", locale: Locale = Locale.getDefault()): String? {
    return safeOperation {
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.format(this)
    }
}

/**
 * Gets the current day of the week's name based on the provided format and locale.
 *
 * @param format The date format to use for the day of the week.  Defaults to "EEEE" (full name).
 * @param locale The locale to use for the day of the week name. Defaults to the default locale.
 * @return The current day of the week's name as a string, or null if an error occurs during formatting.
 */
fun getCurrentDayOfWeekName(
    format: String = "EEEE",
    locale: Locale = Locale.getDefault()
): String? {
    return Date().getDayOfWeekName(format, locale)
}

/**
 * Returns the day of the month for this Date object, considering the specified locale.
 *
 * @param locale The locale to use for determining the day of the month. Defaults to the device's default locale.
 * @return The day of the month (1-31).
 */
fun Date.getDayOfMonth(locale: Locale = Locale.getDefault()): Int {
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
fun getCurrentDayOfMonth(locale: Locale = Locale.getDefault()): Int {
    return Date().getDayOfMonth(locale)
}

/**
 * Gets the month of the year for this date, using the specified locale.
 *
 * Months are 1-indexed (January = 1, February = 2, ..., December = 12).
 *
 * @param locale The locale to use for determining the month. Defaults to the default locale.
 * @return The month of the year (1-12).
 */
fun Date.getMonthOfYear(locale: Locale = Locale.getDefault()): Int {
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
fun getCurrentMonthOfYear(locale: Locale = Locale.getDefault()): Int {
    return Date().getMonthOfYear(locale)
}

/**
 * Returns the name of the month for this Date object, formatted according to the specified format and locale.
 *
 * @param format The date format pattern to use.  Defaults to "MMMM" (full month name).
 * @param locale The locale to use for month name localization. Defaults to the default locale.
 * @return The formatted month name as a String, or null if an exception occurs during formatting.
 */
fun Date.getMonthName(format: String = "MMMM", locale: Locale = Locale.getDefault()): String? {
    return safeOperation {
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.format(this)
    }
}

/**
 * Gets the name of the current month based on the provided format and locale.
 *
 * @param format The date format to use for the month name.  Defaults to "MMMM" (full month name).
 * @param locale The locale to use for the month name. Defaults to the device's default locale.
 * @return The name of the current month as a String, or null if an error occurs during the operation.
 */
fun getCurrentMonthName(format: String = "MMMM", locale: Locale = Locale.getDefault()): String? {
    return safeOperation {
        Date().getMonthName(format, locale)
    }
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
fun getCurrentYear(): Int {
    return Date().year()
}

/**
 * Returns the day of the month of the previous day.
 *
 * @return The day of the month of the previous day (an integer between 1 and 31).
 */
fun Date.getPreviousDay(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getPreviousDay
        add(Calendar.DAY_OF_MONTH, -1)
    }
    return calendar.get(Calendar.DAY_OF_MONTH)
}

/**
 * Returns the previous day's date as an integer.
 *
 * @return The day of the month of the previous day (an integer between 1 and 31).
 */
fun getPreviousDay(): Int {
    return Date().getPreviousDay()
}

/**
 * Returns the day of the month for the day after this date.
 *
 * This function creates a Calendar instance, sets it to this date, adds one day,
 * and then returns the day of the month from the resulting Calendar.
 *
 * @return The day of the month (1-31) for the day after this date.
 */
fun Date.getNextDay(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getNextDay
        add(Calendar.DAY_OF_MONTH, 1)
    }
    return calendar.get(Calendar.DAY_OF_MONTH)
}

/**
 * Returns the day of the month for the day after this date.
 *
 * This function creates a Calendar instance, sets it to this date, adds one day,
 * and then returns the day of the month from the resulting Calendar.
 *
 * @return The day of the month (1-31) for the day after this date.
 */
fun getNextDay(): Int {
    return Date().getNextDay()
}

/**
 * Returns the month (1-12) of the previous month relative to this Date.
 * Note that the returned month is 1-based (January = 1, February = 2, etc.),
 *
 * @return The month (1-12) of the previous month.
 */
fun Date.getPreviousMonth(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getPreviousMonth
        add(Calendar.MONTH, -1)
    }
    return calendar.get(Calendar.MONTH) + 1
}

/**
 * Returns the name of the previous month relative to this Date object.
 *
 * @param format The date format to use for the month name.  Defaults to "MMMM" (full month name).
 * @param locale The locale to use for the month name. Defaults to the default locale.
 * @return The name of the previous month, formatted according to the specified format and locale, or null if an error occurs during the calculation.
 */
fun Date.getPreviousMonthName(
    format: String = "MMMM",
    locale: Locale = Locale.getDefault()
): String? {
    return safeOperation {
        val calendar = Calendar.getInstance().apply {
            time = this@getPreviousMonthName
            add(Calendar.MONTH, -1)
        }
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.format(calendar.time)
    }
}

/**
 * Gets the name of the previous month, formatted according to the specified format and locale.
 *
 * @param format The desired date format.  Defaults to "MMMM" (full month name).
 * @param locale The locale to use for formatting the month name. Defaults to the default locale.
 * @return The name of the previous month formatted according to the given parameters, or null if an error occurs during date processing.
 */
fun getPreviousMonthName(format: String = "MMMM", locale: Locale = Locale.getDefault()): String? {
    return Date().getPreviousMonthName(format, locale)
}

/**
 * Retrieves the previous month's number.
 *
 * This method utilizes the current date to determine and return the number representing the previous month.
 * January is represented as 1, February as 2, and so on.  The returned value will always be between 1 and 12.
 *
 * @return An integer representing the previous month (1-12).
 */
fun getPreviousMonth(): Int {
    return Date().getPreviousMonth()
}

/**
 * Returns the month number (1-12) of the next month after this date.
 *
 * This method takes the current date and calculates the month number of the following month.
 * The month numbers are 1-based (January = 1, February = 2, ..., December = 12).
 *
 * @return The month number (1-12) of the next month.
 */
fun Date.getNextMonth(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getNextMonth
        add(Calendar.MONTH, 1)
    }
    return calendar.get(Calendar.MONTH) + 1
}

/**
 * Returns the name of the month following the date represented by this [Date] object.
 *
 * @param format The date format string to use.  Defaults to "MMMM" (full month name).
 * @param locale The locale to use for formatting the month name. Defaults to the default locale.
 * @return The name of the next month, formatted according to the specified format and locale,
 *         or null if an error occurs during date manipulation.
 */
fun Date.getNextMonthName(format: String = "MMMM", locale: Locale = Locale.getDefault()): String? {
    return safeOperation {
        val calendar = Calendar.getInstance().apply {
            time = this@getNextMonthName
            add(Calendar.MONTH, 1)
        }
        val dateFormat = SimpleDateFormat(format, locale)
        dateFormat.format(calendar.time)
    }
}

/**
 * Gets the name of the next month, formatted according to the specified format and locale.
 *
 * @param format The date format string to use.  Defaults to "MMMM" (full month name).  See [java.text.SimpleDateFormat] for format options.
 * @param locale The locale to use for formatting. Defaults to the device's default locale.
 * @return The name of the next month, formatted according to the specified parameters, or null if an error occurs during formatting.
 */
fun getNextMontName(format: String = "MMMM", locale: Locale = Locale.getDefault()): String? {
    return Date().getNextMonthName(format, locale)
}

/**
 * Returns the month number (1-12) of the next month after this date.
 *
 * This method takes the current date and calculates the month number of the following month.
 * The month numbers are 1-based (January = 1, February = 2, ..., December = 12).
 *
 * @return The month number (1-12) of the next month.
 */
fun getNextMonth(): Int {
    return Date().getNextMonth()
}

/**
 * Returns the year of the date one year prior to this date.
 *
 * @return The year of the date one year before this date.
 */
fun Date.getPreviousYear(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getPreviousYear
        add(Calendar.YEAR, -1)
    }
    return calendar.get(Calendar.YEAR)
}

/**
 * Returns the previous year based on the current date.
 *
 * @return The year before the current year.
 */
fun getPreviousYear(): Int {
    return Date().getPreviousYear()
}

/**
 * Returns the year of the date one year after the current date.
 *
 * @return The year of the date one year after the current date.
 */
fun Date.getNextYear(): Int {
    val calendar = Calendar.getInstance().apply {
        time = this@getNextYear
        add(Calendar.YEAR, 1)
    }
    return calendar.get(Calendar.YEAR)
}

/**
 * Returns the year of the date one year after the current date.
 *
 * @return The year of the date one year after the current date.
 */
fun getNextYear(): Int {
    return Date().getNextYear()
}













