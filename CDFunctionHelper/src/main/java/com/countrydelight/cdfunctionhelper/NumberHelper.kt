package com.countrydelight.cdfunctionhelper

import java.util.Locale


/**
 * Formats this Double value to a String with a fixed number of decimal places.
 *
 * @param decimalPlaces The number of decimal places to include in the formatted string.
 *                      Must be a non-negative integer.  If zero, no decimal point will be shown.
 * @return A string representation of this Double value with the specified number of decimal places.
 *         Uses the default locale for formatting.  Returns an empty string if [decimalPlaces] is negative.
 */
fun Double.toFixedDecimal(decimalPlaces: Int): String {
    return if (decimalPlaces > 0) {
        String.format(Locale.getDefault(), "%.${decimalPlaces}f", this)
    } else {
        this.toString()
    }
}

/**
 * Formats a Float to a String with a specified number of decimal places.
 *
 * Uses the default locale to ensure correct formatting based on the user's system settings.
 *
 * @param decimalPlaces The number of decimal places to include in the formatted string.  Must be non-negative.
 * @return A String representation of the Float with the specified number of decimal places.
 * @throws IllegalArgumentException if `decimalPlaces` is negative.
 */
fun Float.toFixedDecimal(decimalPlaces: Int): String {
    return if (decimalPlaces > 0) {
        String.format(Locale.getDefault(), "%.${decimalPlaces}f", this)
    } else {
        this.toString()
    }
}