package com.csci448.avelychko.mis_match

import SimpleCameraPreview
import android.app.Activity
import android.content.Context
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.csci448.avelychko.mis_match.presentation.HomeScreen
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.ui.theme.MisMatchTheme
import com.csci448.avelychko.mis_match.util.CameraUtility
import com.csci448.avelychko.mis_match.presentation.Tests
import com.csci448.avelychko.mis_match.presentation.navigation.MisMatchNavHost

class MainActivity : ComponentActivity() {
    private lateinit var cameraUtility: CameraUtility
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel = MisMatchViewModel();
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
                    MisMatchScreen(viewModel, this@MainActivity, cameraUtility, permissionLauncher)

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
    permissionLauncher: ActivityResultLauncher<Array<String>>
) {
    val navController = rememberNavController()
    MisMatchNavHost(navController = navController, activity = activity, viewModel = viewModel,
        cameraUtility = cameraUtility, permissionLauncher = permissionLauncher )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = MisMatchViewModel();
    MisMatchTheme {
    }
}
