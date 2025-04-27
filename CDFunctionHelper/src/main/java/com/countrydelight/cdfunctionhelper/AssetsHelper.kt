package com.countrydelight.cdfunctionhelper

import android.content.Context
import com.countrydelight.cdfunctionhelper.internal_utils.getMoshiInstance
import com.countrydelight.cdfunctionhelper.internal_utils.safeOperation
import com.squareup.moshi.Moshi


/**
 * Reads a file from the assets folder.
 *
 * @param context The Android context.  Required to access the assets folder.
 * @param fileName The name of the file to read (without the extension).
 * @return The content of the file as a String, or null if the file is not found or an error occurs during reading.
 */
private fun readAssetsFile(context: Context, fileName: String): String {
    return context.assets.open(fileName).bufferedReader().use { it.readText() }
}

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
        readAssetsFile(this, fileName)
    }
}

/**
 * Reads a JSON file from the assets folder and deserializes it into an object of the specified class.
 *
 * @param fileName The name of the JSON file in the assets folder.
 * @param clazz The class of the object to deserialize the JSON into.
 * @param onError An optional callback function that is invoked if an exception occurs during the file reading or deserialization process.  The exception is passed as an argument.
 * @return The deserialized object, or null if an exception occurs and no onError callback is provided, or if the file is not found.
 */
fun <T> Context.readJsonFile(
    fileName: String,
    clazz: Class<T>,
    moshi: Moshi = getMoshiInstance(),
    onError: ((e: Exception) -> Unit)? = null
): T? {
    return safeOperation(onError) {
        val jsonString = readAssetsFile(this, fileName)
        val adapter = moshi.adapter(clazz)
        adapter.fromJson(jsonString)
    }
}


