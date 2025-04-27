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
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED
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

/**
 * Returns a list of permissions from the given list that are not granted in the current context.
 *
 * @param permissions A list of permissions to check.
 * @return A list of permissions from the input list that are not granted in the current context.  Returns an empty list if all permissions are granted.
 */
fun Context.getNotGrantedPermissions(permissions: List<String>): List<String> {
    val notGrantedPermissions = arrayListOf<String>()
    permissions.forEach { permission ->
        if (isPermissionNotGranted(permission)) {
            notGrantedPermissions.add(permission)
        }
    }
    return notGrantedPermissions
}