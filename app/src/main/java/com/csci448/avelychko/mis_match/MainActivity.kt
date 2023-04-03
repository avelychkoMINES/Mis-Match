package com.csci448.avelychko.mis_match

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.FileProvider
import androidx.navigation.compose.rememberNavController
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.presentation.navigation.MisMatchNavHost
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.ui.theme.MisMatchTheme
import com.csci448.mis_match_start.presentation.HomeScreen
import java.io.File
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MisMatchViewModel();

        val takePhotoLauncher = registerForActivityResult(
            ActivityResultContracts.TakePicture()
        ) { didTakePhoto ->
            if(didTakePhoto) {
                val photograph = Photograph(photoName)
                viewModel.addPhotograph(photograph)
            }
        }

        takePicture = {
            photoName = "IMG_${Date()}.JPG"
            val photoFile = File(this@MainActivity, photoName)
            val photoUri = FileProvider.getUriForFile(this@MainActivity,
                "edu.mines.csci448.examples.camera.fileprovider",
                photoFile)
            takePhotoLauncher.launch(photoUri)
        }

        super.onCreate(savedInstanceState)
        setContent {
            MisMatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MisMatchScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MisMatchScreen(viewModel: MisMatchViewModel) {
    val navController = rememberNavController()
    MisMatchNavHost(navController = navController, viewModel = viewModel)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = MisMatchViewModel();
    MisMatchTheme {
        MisMatchScreen(viewModel = viewModel)
    }
}