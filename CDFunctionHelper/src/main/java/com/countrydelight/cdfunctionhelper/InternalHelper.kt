package com.countrydelight.cdfunctionhelper


/**
 * Executes a given operation safely, handling potential exceptions.
 *
 * @param operation The operation to execute.  This should be a lambda expression or method reference.
 * @param onException An optional lambda expression to handle exceptions. If provided, this will be invoked with the caught exception.
 *                    If not provided, the exception's stack trace will be printed to the console.
 * @param <T> The type of the result returned by the operation.
 * @return The result of the operation if successful; otherwise, null.
 *
 * @throws SecurityException if the security manager exists and its checkPermission method doesn't allow access to the operation.
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