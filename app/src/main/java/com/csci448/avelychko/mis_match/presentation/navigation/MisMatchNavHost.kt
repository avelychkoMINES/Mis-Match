package com.csci448.avelychko.mis_match.presentation.navigation
import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility

@Composable
fun MisMatchNavHost(
    navController: NavHostController,
    viewModel: MisMatchViewModel,
    activity: Activity,
    cameraUtility: CameraUtility,
    permissionLauncher: ActivityResultLauncher<Array<String>>
) {
    NavHost( navController = navController, startDestination = IScreenSpec.root ) {
        navigation(IScreenSpec.startDestination, IScreenSpec.root) {
            IScreenSpec.allScreens.forEach { screen ->
                if(screen != null) {
                    composable(route = screen.route, ) {
                        screen.Content(
                            viewModel = viewModel,
                            navController = navController,
                            activity = activity,
                            cameraUtility = cameraUtility,
                            permissionLauncher = permissionLauncher
                        )
                    }
                }
            }
        }

    }
}
