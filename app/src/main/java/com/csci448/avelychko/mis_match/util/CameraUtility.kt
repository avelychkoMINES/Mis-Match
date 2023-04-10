package com.csci448.avelychko.mis_match.util

import android.Manifest
import android.Manifest.permission.*
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executor

class CameraUtility(context: Context) {
    fun checkPermissionAndGetCamera(activity: Activity,
                                      permissionLauncher: ActivityResultLauncher<Array<String>>) {

        if (ContextCompat.checkSelfPermission( activity, CAMERA) == PERMISSION_GRANTED) {
            Log.d("CameraUtility", "have permission")
            //TODO
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    CAMERA
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
                permissionLauncher.launch(arrayOf( CAMERA ))
            }
        }
    }
}
