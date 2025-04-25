package com.countrydelight.cdfunctionhelper

import android.content.Context


fun Context.readAssetsFile(fileName: String, onError: ((e: Exception) -> Unit)? = null): String? {
    return try {
        assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (exception: Exception) {
        onError?.invoke(exception)
        null
    }
}