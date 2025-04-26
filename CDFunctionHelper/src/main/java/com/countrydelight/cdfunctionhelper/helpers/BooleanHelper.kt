package com.countrydelight.cdfunctionhelper.helpers

/**
 * Checks if this nullable Boolean is true.
 *
 * @return `true` if this Boolean is not null and is equal to `true`; otherwise, `false`.
 */
fun Boolean?.isTrue() = this == true

/**
 * Checks if this boolean value is false.
 *
 * @return True if this value is `false`, false otherwise.  Returns `false` if this is null.
 */
fun Boolean?.isFalse() = this == false