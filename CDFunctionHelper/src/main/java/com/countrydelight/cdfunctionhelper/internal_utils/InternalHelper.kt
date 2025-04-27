package com.countrydelight.cdfunctionhelper.internal_utils

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory


/**
 * Executes a given operation safely, handling potential exceptions.
 *
 * @param operation The operation to execute.  This should be a lambda expression or method reference.
 * @param onException An optional lambda expression to handle exceptions. If provided, this will be invoked with the caught exception.
 *                    If not provided, the exception's stack trace will be printed to the console.
 * @param <T> The type of the result returned by the operation.
 * @return The result of the operation if successful; otherwise, null.
 *
 */
internal fun <T> safeOperation(onException: ((Exception) -> Unit)? = null, operation: () -> T): T? {
    return try {
        operation()
    } catch (exception: Exception) {
        if (onException == null) {
            exception.printStackTrace()
        } else {
            onException.invoke(exception)
        }
        null
    }
}


/**
 * Returns a Moshi instance configured with a KotlinJsonAdapterFactory.
 *
 * This method creates a new Moshi builder, adds the KotlinJsonAdapterFactory for handling Kotlin data classes,
 * and then builds and returns the Moshi instance.  This ensures proper serialization and deserialization
 * of Kotlin objects.
 *
 * @return A Moshi instance configured with a KotlinJsonAdapterFactory.
 */
internal fun getMoshiInstance(): Moshi {
    return Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
}