package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.presentation.HomeScreen
import com.csci448.avelychko.mis_match.util.CameraUtility

object HomeScreenSpec : IScreenSpec {
    override val route: String = "home"

    @Composable
    override fun Content(
        viewModel: MisMatchViewModel,
        navController: NavController,
        activity: Activity,
        cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>
    ) {
        HomeScreen(viewModel = viewModel,
            onOutfitBuilderClick = {
            navController.navigate("outfit builder")
        }, onSavedOutfitsClick =
        {
            navController.navigate("saved outfits")
        }, onStyleGeneratorClick =
        {
            navController.navigate("style generator")
        }, onClosetClick =
        {
            navController.navigate("closet")
        },
        {
            cameraUtility.checkPermissionAndGetCamera(activity, permissionLauncher)
            navController.navigate("camera")
        })
    }
}
