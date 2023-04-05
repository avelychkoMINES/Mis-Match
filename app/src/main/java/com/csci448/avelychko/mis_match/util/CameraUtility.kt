package com.csci448.avelychko.mis_match.util

import android.Manifest
import android.Manifest.permission.*
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.location.Location
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class CameraUtility(context: Context) {
    fun checkPermissionAndGetCamera(activity: Activity,
                                      permissionLauncher: ActivityResultLauncher<Array<String>>) {

        if (ContextCompat.checkSelfPermission( activity, CAMERA) == PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission( activity,
                WRITE_EXTERNAL_STORAGE
            )
            == PERMISSION_GRANTED) {
            Log.d("CameraUtility", "have permission")
            //TODO
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    CAMERA
                )
                || ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    WRITE_EXTERNAL_STORAGE
                )) {
                Log.d("CameraUtility", "permission denied")
                //val currentContext = LocalContext.current
                Toast
                    .makeText(
                        activity,
                        "We must access your camera",
                        Toast.LENGTH_SHORT
                    )
                    .show()
            } else {
                Log.d("CameraUtility", "getting permission")
                permissionLauncher.launch(arrayOf( CAMERA, WRITE_EXTERNAL_STORAGE))
            }
        }
    }
}
