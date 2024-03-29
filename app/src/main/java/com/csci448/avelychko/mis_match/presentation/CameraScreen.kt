package com.csci448.avelychko.mis_match.presentation

import SimpleCameraPreview
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.data.PhotographRepository
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CameraView() {
    val imageCapture = ImageCapture.Builder()
        .build()
    val context = LocalContext.current
    val mMediaPlayer = MediaPlayer.create(context, R.raw.camera)
    var type = remember {
        ""
    }

    Box {
        SimpleCameraPreview(imageCapture)
        Column(
            modifier = Modifier
                .fillMaxHeight()
        )
        {
            Box(modifier = Modifier.weight(0.8f)) {

            }
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .background(Color.Black)
            ) {
                Column(Modifier.padding(vertical = 5.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Tops",
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier.clickable {
                                type = "Tops-"
                            });
                        Text(text = "Bottoms",
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier.clickable {
                                type = "Bottoms-"
                            });
                        Text(text = "Shoes",
                            color = Color.White,
                            fontSize = 24.sp,
                            modifier = Modifier.clickable {
                                type = "Shoes-"
                            });
                    }
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        IconButton(onClick = {
                            takePhoto(imageCapture, context, type)
                            mMediaPlayer.start()
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_circle_24),
                                contentDescription = "",
                                tint = Color.White,
                                modifier = Modifier.size(100.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

// links:
// https://developer.android.com/guide/topics/providers/content-provider-basics
// https://developer.android.com/reference/android/provider/MediaStore.Images.Media
private fun takePhoto(imageCapture: ImageCapture?, context: Context, type: String) {
    Log.d(TAG, "takephoto() called")

    // Get a stable reference of the modifiable image capture use case
    val imageCapture = imageCapture ?: return

    // Create time stamped name and MediaStore entry.
    val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    val name = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
        .format(System.currentTimeMillis())
    val contentValues = ContentValues()
        .apply {
        put(MediaStore.MediaColumns.DISPLAY_NAME, type + name)
        put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
            put(MediaStore.Images.Media.RELATIVE_PATH, "Pictures/CameraX-Image/")
        }
    }
    Log.d(TAG, "contentValues: $contentValues")

    // Create output options object which contains file + metadata
    val outputOptions = ImageCapture.OutputFileOptions
        .Builder(context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues)
        .build()

    // data/data/ - probably don't need to make a file any more
    Log.d(TAG, "outputoptions: $outputOptions")

    // Set up image capture listener, which is triggered after photo has
    // been taken
    imageCapture.takePicture(
        outputOptions,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback {
            override fun onError(exc: ImageCaptureException) {
                Log.e(TAG, "Photo capture failed: ${exc.message}", exc)
            }

            override fun
                    onImageSaved(output: ImageCapture.OutputFileResults){
                val msg = "Photo capture succeeded: ${output.savedUri}"
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                Log.d(TAG, msg)
            }
        }
    )

    updatePhoto(context)
}

private fun updatePhoto(context: Context) {

    Log.d("getphoto", "cursor start")
    Log.d("getphoto", "media ${MediaStore.Images.Media.EXTERNAL_CONTENT_URI}")

    val cursor = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        null,
        null,
        null,
        null
    )
    if (cursor != null) {
        Log.d("getphoto", "cursor not null")
        Log.d("getphoto", "cursorTop $cursor")

        Log.d("getphoto", "cursorcount ${cursor.count}")

        PhotographRepository.top.clear()
        PhotographRepository.bottom.clear()
        PhotographRepository.shoe.clear()

        while (cursor.moveToNext()) {
            Log.d("getphoto", "cursorTop $cursor")
            val absolutePathOfImage = cursor.getString(
                cursor.getColumnIndexOrThrow(
                    MediaStore.MediaColumns.DATA
                )
            )
            Log.d("getphoto", "absolutepath $absolutePathOfImage")

            if (absolutePathOfImage.contains("Tops")) {
                PhotographRepository.top.add(Photograph(absolutePathOfImage))
            } else if (absolutePathOfImage.contains("Bottoms")) {
                PhotographRepository.bottom.add(Photograph(absolutePathOfImage))
            } else if (absolutePathOfImage.contains("Shoes")) {
                PhotographRepository.shoe.add(Photograph(absolutePathOfImage))
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun CameraPreview() {
//    CameraView()
//}
