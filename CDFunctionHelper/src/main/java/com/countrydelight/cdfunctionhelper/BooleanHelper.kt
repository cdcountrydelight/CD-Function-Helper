package com.countrydelight.cdfunctionhelper


/**
 * Returns `true` if the Boolean is `null`, otherwise returns the Boolean's value.
 * Useful for avoiding null checks when a default of `true` is desired.
 */
fun Boolean?.orTrue() = this ?: true

/**
 * Returns `false` if the Boolean is `null`, otherwise returns the Boolean's value.
 * Useful for avoiding null checks when a default of `false` is desired.
 */
fun Boolean?.orFalse() = this ?: false