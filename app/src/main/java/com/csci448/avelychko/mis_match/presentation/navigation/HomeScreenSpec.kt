package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.presentation.HomeScreen
import com.csci448.avelychko.mis_match.util.CameraUtility
import kotlinx.coroutines.CoroutineScope

object HomeScreenSpec : IScreenSpec {
    override val route: String = "home"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route
    override var title: Int
        get() = R.string.app_name
        set(value) {
            title = value
        }

    @Composable
    override fun Content(
        viewModel: PhotographViewModel,
        navController: NavController,
        activity: Activity,
        cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        HomeScreen(viewModel = viewModel,
            onOutfitBuilderClick = {
            navController.navigate("outfit builder")
        }, onSavedOutfitsClick =
        {
            navController.navigate("saved outfits")
        }, onClosetClick =
        {
            navController.navigate("closet")
        }, onSettingsClick =
            {
                navController.navigate("settings")
            },
            onCameraClick = {
                cameraUtility.checkPermissionAndGetCamera(activity, permissionLauncher)
                navController.navigate("camera")
            })
    }
}
