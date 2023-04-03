package edu.mines.csci448.examples.camera

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProvider
import com.csci448.avelychko.mis_match.MisMatchScreen
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.presentation.PhotographScreen
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import edu.mines.csci448.examples.camera.data.Photograph
import edu.mines.csci448.examples.camera.presentation.MainActivityScreen
import edu.mines.csci448.examples.camera.presentation.viewmodel.PhotographViewModel
import edu.mines.csci448.examples.camera.presentation.viewmodel.PhotographViewModelFactory
import edu.mines.csci448.examples.camera.ui.theme.CameraTheme
import java.io.File
import java.util.*

class photoMainActivity : ComponentActivity() {
    companion object {
        private const val LOG_TAG = "448.MainActivity"
    }

    private lateinit var photographViewModel: PhotographViewModel

    // TODO #2 make camera launcher

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MisMatchViewModel();
            super.onCreate(savedInstanceState)

            val factory = PhotographViewModelFactory(this)
            photographViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]

            // TODO #2A - register camera launcher
        val takePhotoLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { didTakePhoto ->
            if(didTakePhoto) {
                val photograph = Photograph(photoName)
                photographViewModel.addPhotograph(photograph)
            }
        }

            setContent {
                CameraTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        MisMatchScreen(viewModel)

                        PhotographScreen(
                            photographViewModel = photographViewModel,
                            takePicture = {
                                photoName = "IMG_${Date()}.JPG"
                                val photoFile = File(this@photoMainActivity, photoName)
                                val photoUri = FileProvider.getUriForFile(this@photoMainActivity,
                                    "edu.mines.csci448.examples.camera.fileprovider",
                                    photoFile)
                                takePhotoLauncher.launch(photoUri)
                            }
                        )
                    }
                }
            }
    }
}