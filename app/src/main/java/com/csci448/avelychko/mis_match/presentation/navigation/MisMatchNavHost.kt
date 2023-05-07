package com.csci448.avelychko.mis_match.presentation.navigation
import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navigation
import com.csci448.avelychko.mis_match.NotificationReceiver
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility
import kotlinx.coroutines.CoroutineScope

@Composable
fun MisMatchNavHost(
    navController: NavHostController,
    viewModel: PhotographViewModel,
    activity: Activity,
    cameraUtility: CameraUtility,
    permissionLauncher: ActivityResultLauncher<Array<String>>,
    coroutineScope: CoroutineScope,
    context: Context,
    modifier: Modifier,
    onNotify: () -> Unit
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = IScreenSpec.root
    ) {
        navigation(
            route = IScreenSpec.root,
            startDestination = IScreenSpec.startDestination
        ) {
            IScreenSpec.allScreens.forEach { (_, screen) ->
                if(screen != null) {
                    composable(
                        route = screen.route,
                        arguments = screen.arguments
                    ) { navBackStackEntry ->
                        screen.Content(
                            navController = navController,
                            navBackStackEntry = navBackStackEntry,
                            viewModel = viewModel,
                            context = context,
                            coroutineScope = coroutineScope,
                            activity = activity,
                            cameraUtility = cameraUtility,
                            permissionLauncher = permissionLauncher,
                            onNotify = onNotify
                        )
                    }
                }
            }
        }
    }
}
