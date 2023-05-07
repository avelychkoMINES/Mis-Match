package com.csci448.avelychko.mis_match

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.ui.theme.MisMatchTheme
import com.csci448.avelychko.mis_match.util.CameraUtility
import com.csci448.avelychko.mis_match.presentation.navigation.MisMatchNavHost
import androidx.lifecycle.ViewModelProvider
import com.csci448.avelychko.mis_match.presentation.navigation.MisMatchTopBar
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModelFactory

class MainActivity : ComponentActivity() {
    private lateinit var cameraUtility: CameraUtility
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var viewModel: PhotographViewModel
    private lateinit var notificationPermissionLauncher: ActivityResultLauncher<Array<String>>
    private val notificationReceiver = NotificationReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = PhotographViewModelFactory(this)
        viewModel = ViewModelProvider(this, factory) [factory.getViewModelClass()]

        permissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                // process if permissions were granted
                cameraUtility.checkPermissionAndGetCamera(this@MainActivity, permissionLauncher)
            }
            
         notificationPermissionLauncher =
           registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
               notificationReceiver.checkPermissionAndScheduleAlarm(this, notificationPermissionLauncher)
           }
           
        cameraUtility = CameraUtility(this@MainActivity)


        setContent {
            val navController = rememberNavController()
            val coroutineScope = rememberCoroutineScope()
            val context = LocalContext.current

            MisMatchTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(topBar = {MisMatchTopBar(
                        navHostController = navController,
                        photographViewModel = viewModel,
                        context = context
                    )}) {
                            paddingValues -> MisMatchNavHost(
                        navController = navController,
                        viewModel = viewModel,
                        context = context, modifier = Modifier.padding(paddingValues),
                        coroutineScope = coroutineScope,
                        cameraUtility = cameraUtility,
                        activity = this@MainActivity,
                        permissionLauncher = permissionLauncher
                    )
                    }
                    //SamodelkinListScreen(characterList = samodelkinViewModel.characters) {}

                    //MisMatchScreen(viewModel, this@MainActivity, cameraUtility, permissionLauncher)
                }
            }
        }
    }
}

//@Composable
//fun MisMatchScreen(
//    viewModel: PhotographViewModel,
//    activity: Activity,
//    cameraUtility: CameraUtility,
//    permissionLauncher: ActivityResultLauncher<Array<String>>
//) {
//    val navController = rememberNavController()
//    MisMatchNavHost(
//        navController = navController, activity = activity, viewModel = viewModel,
//        cameraUtility = cameraUtility, permissionLauncher = permissionLauncher)
//}

//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    val viewModel = MisMatchViewModel();
//    MisMatchTheme {
//    }
//}
