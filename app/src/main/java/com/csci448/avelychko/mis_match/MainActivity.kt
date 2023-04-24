package com.csci448.avelychko.mis_match

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.csci448.avelychko.mis_match.data.database.PictureRepo
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.ui.theme.MisMatchTheme
import com.csci448.avelychko.mis_match.util.CameraUtility
import com.csci448.avelychko.mis_match.presentation.navigation.MisMatchNavHost
import androidx.camera.core.ImageCapture;

class MainActivity : ComponentActivity() {
    private lateinit var cameraUtility: CameraUtility
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private var imageCapture: ImageCapture? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MisMatchViewModel(PictureRepo.topList, PictureRepo.bottomList, PictureRepo.shoesList);
        super.onCreate(savedInstanceState)
        
        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                // process if permissions were granted
                cameraUtility.checkPermissionAndGetCamera(this@MainActivity, permissionLauncher)
            }
        cameraUtility = CameraUtility(this@MainActivity)

        setContent {
            MisMatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MisMatchScreen(viewModel, this@MainActivity, cameraUtility, permissionLauncher, imageCapture)

//                    HomeScreen(
//                        viewModel = viewModel,
//                        onOutfitBuilderClick = { /*TODO*/ },
//                        onSavedOutfitsClick = { /*TODO*/ },
//                        onStyleGeneratorClick = { /*TODO*/ },
//                        onClosetClick = { /*TODO*/ },
//                        ) {
//                        cameraUtility.checkPermissionAndGetCamera(this@MainActivity, permissionLauncher)
//                    }
                }
            }
        }
    }
}

@Composable
fun MisMatchScreen(
    viewModel: MisMatchViewModel,
    activity: Activity,
    cameraUtility: CameraUtility,
    permissionLauncher: ActivityResultLauncher<Array<String>>,
    imageCapture: ImageCapture?
) {
    val navController = rememberNavController()
    MisMatchNavHost(
        navController = navController, activity = activity, viewModel = viewModel,
        cameraUtility = cameraUtility, permissionLauncher = permissionLauncher, imageCapture = imageCapture )
}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val viewModel = MisMatchViewModel();
//    MisMatchTheme {
//    }
//}
