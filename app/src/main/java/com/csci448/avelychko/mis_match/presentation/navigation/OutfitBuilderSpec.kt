package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.presentation.OutfitBuilderView
import com.csci448.avelychko.mis_match.util.CameraUtility

object OutfitBuilderSpec: IScreenSpec {
    override val route: String
        get() = "outfit builder"

    @Composable
    override fun Content(
        viewModel: MisMatchViewModel, navController: NavController,
        activity: Activity, cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>
    ) {
        OutfitBuilderView(viewModel) { navController.navigate("home") }
    }
}