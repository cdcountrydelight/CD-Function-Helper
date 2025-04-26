package com.countrydelight.cdfunctionhelper

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


/**
 * Checks if the given permission is granted to this context.
 *
 * @param permission The name of the permission to check (e.g., `Manifest.permission.CAMERA`).
 * @return `true` if the permission is granted, `false` otherwise.
 */
fun Context.isPermissionGranted(permission: String): Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

/**
 * Checks if a given permission is *not* granted to the application.
 *
 * @param permission The permission to check (e.g., "android.permission.CAMERA").
 * @return `true` if the permission is not granted; `false` otherwise.
 */
fun Context.isPermissionNotGranted(permission: String): Boolean {
    return !isPermissionGranted(permission)
}

/**
 * Checks if the app should show a rationale for requesting the specified permission.
 * This is useful for explaining to the user why the permission is needed, before actually requesting it.
 *
 * @param permission The permission to check.
 * @return True if the app should show a rationale; otherwise, false.
 *
 * @see ActivityCompat.shouldShowRequestPermissionRationale
 */
fun Activity.shouldShowPermissionRationale(permission: String): Boolean {
    return ActivityCompat.shouldShowRequestPermissionRationale(this, permission)
}