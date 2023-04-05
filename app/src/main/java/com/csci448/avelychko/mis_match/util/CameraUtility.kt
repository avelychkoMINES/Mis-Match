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
    
    private fun takePhoto(
    filenameFormat: String,
    imageCapture: ImageCapture,
    outputDirectory: File,
    executor: Executor,
    onImageCaptured: (Uri) -> Unit,
    onError: (ImageCaptureException) -> Unit
    ) {

        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(filenameFormat, Locale.US).format(System.currentTimeMillis()) + ".jpg"
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        imageCapture.takePicture(outputOptions, executor, object: ImageCapture.OnImageSavedCallback {
            override fun onError(exception: ImageCaptureException) {
                Log.e("kilo", "Take photo error:", exception)
                onError(exception)
            }

            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                val savedUri = Uri.fromFile(photoFile)
                onImageCaptured(savedUri)
            }
        })
    }
}
