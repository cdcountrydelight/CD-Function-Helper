package com.countrydelight.cdfunctionhelper


/**
 * Checks if this string contains only digits.
 *
 * @return `true` if this string is not empty and all its characters are digits, `false` otherwise.
 */
fun String.isDigitOnly() = all { it.isDigit() }

/**
 * Converts this string to an integer, or returns a default value if the conversion fails.
 *
 * @param default The value to return if the string cannot be parsed as an integer.
 * @return The integer representation of this string, or the provided default value if parsing fails.
 */
fun String?.toIntOrDefault(default: Int) = this?.toIntOrNull() ?: default

/**
 * Converts this string to an integer, or returns 0 if the conversion fails.
 *
 * This method attempts to parse this string as an integer using `toIntOrNull()`.
 * If the parsing is successful, the resulting integer is returned.  If the parsing fails (e.g., the string is not a valid integer representation), 0 is returned.
 *
 * @return The integer representation of this string, or 0 if the conversion fails.
 */
fun String?.toIntOrZero() = this.toIntOrDefault(0)

/**
 * Converts this string to a Double, or returns a default value if the conversion fails.
 *
 * @param default The value to return if the string cannot be parsed as a Double.
 * @return The Double representation of this string, or the provided default value if parsing fails.
 */
fun String?.toDoubleOrDefault(default: Double) = this?.toDoubleOrNull() ?: default

/**
 * Converts this string to a Double, or returns 0.0 if the conversion fails.
 *
 * This function attempts to parse the string as a Double.
 * If the parsing is successful, the resulting Double value is returned.
 * If the parsing fails (e.g., the string is not a valid number), 0.0 is returned.
 *
 * @return The Double representation of this string, or 0.0 if the conversion fails.
 */
fun String?.toDoubleOrZero() = this.toDoubleOrDefault(0.0)


/**
 * Converts a string to title case, capitalizing the first letter of each word.
 *
 * Words are defined as sequences of characters separated by the specified delimiter.  By default,
 * the delimiter is a space character.
 *
 * @param delimiter The delimiter used to separate words.  Defaults to a single space character (" ").
 * @return A new string with the first letter of each word capitalized, and the rest of the word in lowercase.
 *         If the input string is empty, an empty string is returned.
 */
fun String.toTitleCase(delimiter: String = " "): String {
    return this.lowercase()
        .split(delimiter)
        .joinToString(" ") { word ->
            word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
        }
}

/**
 * Capitalizes the first character of this string.
 *
 * If the first character is a lowercase letter, it is converted to its title case equivalent.
 * Otherwise, the first character remains unchanged.  The rest of the string is left untouched.
 *
 * @return A new string with the first character capitalized.  Returns the original string if it is empty.
 */
fun String.capitalizeFirstChar(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase() else it.toString()
    }
}

/**
 * Returns this string if it's not null, otherwise returns the result of the provided lambda.
 *
 * @param defaultValue A lambda expression that returns a default string value if this string is null.
 * @return This string if it's not null; otherwise, the result of the `defaultValue` lambda.
 */
fun String?.ifNull(defaultValue: () -> String): String {
    return this ?: defaultValue()
}

/**
 * Returns the string itself if it's not null and not blank; otherwise, returns the result of the provided default value lambda.
 *
 * @param defaultValue A lambda expression that returns a String to be used as the default value if the receiver string is null or blank.
 * @return The receiver string if it's not null and not blank, otherwise the result of the `defaultValue` lambda.
 */
fun String?.ifNullOrBlank(defaultValue: () -> String): String {
    return if (this.isNullOrBlank()) defaultValue() else this
}

/**
 * Returns the string itself if it's not null and not empty; otherwise, returns the result of the provided lambda expression.
 *
 * @param defaultValue A lambda expression that returns a default string value if the receiver string is null or empty.
 * @return The receiver string if it's not null and not empty, otherwise the result of the `defaultValue` lambda.
 */
fun String?.ifNullOrEmpty(defaultValue: () -> String): String {
    return if (this.isNullOrEmpty()) defaultValue() else this
}

