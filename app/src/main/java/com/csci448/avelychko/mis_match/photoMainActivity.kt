package com.csci448.avelychko.mis_match

//import android.app.Fragment
//import android.net.Uri
//import android.os.Bundle
//import android.util.Log
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.activity.result.ActivityResultCallback
//import androidx.activity.result.ActivityResultLauncher
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.compose.foundation.layout.*
//import androidx.compose.material3.*
//import androidx.compose.ui.Modifier
//import androidx.core.content.FileProvider
//import androidx.lifecycle.ViewModelProvider
//import com.csci448.avelychko.mis_match.MisMatchScreen
//import com.csci448.avelychko.mis_match.data.Photograph
//import com.csci448.avelychko.mis_match.presentation.PhotographScreen
//import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
//import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
//import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModelFactory
//import java.io.File
//import java.util.*
//
//class photoMainActivity : Fragment() {
//    companion object {
//        private const val LOG_TAG = "448.MainActivity"
//    }
//
//    private lateinit var photographViewModel: PhotographViewModel
//
//    val takePhotoLauncher = registerForActivityResult(
//        ActivityResultContracts.TakePicture()
//    ) { didTakePhoto ->
//        if(didTakePhoto) {
//            val photograph = Photograph("testImage.jpg")
//            photographViewModel.addPhotograph(photograph)
//        }
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        val viewModel = MisMatchViewModel();
//            super.onCreate(savedInstanceState)
//
//            val factory = PhotographViewModelFactory(this)
//            photographViewModel = ViewModelProvider(this, factory)[factory.getViewModelClass()]
//
//        takePicture = {
//            photoName = "IMG_${Date()}.JPG"
//            val photoFile = File(this@MainActivity, photoName)
//            val photoUri = FileProvider.getUriForFile(this@MainActivity,
//                "edu.mines.csci448.examples.camera.fileprovider",
//                photoFile)
//            takePhotoLauncher.launch(photoUri)
//        }
//
//        val takePhotoLauncher = registerForActivityResult(
//            ActivityResultContracts.TakePicture()
//        ) { didTakePhoto ->
//            if(didTakePhoto) {
//                val photograph = Photograph(photoName)
//                photographViewModel.addPhotograph(photograph)
//            }
//        }
//
//            setContent {
//                CameraTheme {
//                    // A surface container using the 'background' color from the theme
//                    Surface(
//                        modifier = Modifier.fillMaxSize(),
//                        color = MaterialTheme.colorScheme.background
//                    ) {
//                        MisMatchScreen(viewModel)
//
//                        PhotographScreen(
//                            photographViewModel = photographViewModel,
//                            takePicture = {
//                                photoName = "IMG_${Date()}.JPG"
//                                val photoFile = File(this@photoMainActivity, photoName)
//                                val photoUri = FileProvider.getUriForFile(this@photoMainActivity,
//                                    "edu.mines.csci448.examples.camera.fileprovider",
//                                    photoFile)
//                                takePhotoLauncher.launch(photoUri)
//                            }
//                        )
//                    }
//                }
//            }
//    }
//}