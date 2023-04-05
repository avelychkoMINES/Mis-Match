package com.csci448.avelychko.mis_match

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
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.presentation.navigation.MisMatchNavHost
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.ui.theme.MisMatchTheme
import com.csci448.avelychko.mis_match.util.CameraUtility
import com.csci448.mis_match_start.presentation.HomeScreen

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
                    //MisMatchScreen(viewModel)
                    HomeScreen(
                        viewModel = viewModel,
                        onOutfitBuilderClick = { /*TODO*/ },
                        onSavedOutfitsClick = { /*TODO*/ },
                        onStyleGeneratorClick = { /*TODO*/ },
                        onClosetClick = { /*TODO*/ },
                        ) {
                        cameraUtility.checkPermissionAndGetCamera(this@MainActivity, permissionLauncher)
                    }
                }
            }
        }
    }
}

@Composable
fun MisMatchScreen(viewModel: MisMatchViewModel) {
    val navController = rememberNavController()
    //MisMatchNavHost(navController = navController, viewModel = viewModel )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val viewModel = MisMatchViewModel();
    MisMatchTheme {
        MisMatchScreen(viewModel = viewModel)
    }
}
