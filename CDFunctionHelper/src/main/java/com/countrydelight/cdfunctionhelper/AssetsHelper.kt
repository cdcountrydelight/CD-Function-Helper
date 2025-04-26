package com.countrydelight.cdfunctionhelper

import android.content.Context

/**
 * Reads a file from the assets folder.
 *
 * @param fileName The name of the file to read.
 * @param onError A lambda function to handle exceptions that may occur during file reading.  If an exception occurs and this parameter is null, the exception is swallowed and null is returned.
 * @return The content of the file as a String, or null if an error occurred and onError was null or did not handle the exception.
 * @throws IllegalArgumentException if fileName is null or empty.  This is handled internally and reported via onError if provided, otherwise null is returned.
 */
fun Context.readAssetsFile(fileName: String, onError: ((e: Exception) -> Unit)? = null): String? {
    return safeOperation(onError) {
        assets.open(fileName).bufferedReader().use { it.readText() }
    }
}

