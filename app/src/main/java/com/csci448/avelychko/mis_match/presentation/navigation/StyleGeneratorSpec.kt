package com.csci448.avelychko.mis_match.presentation.navigation

import StyleScreen
import android.app.Activity
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility

object StyleGeneratorSpec: IScreenSpec {
    override val route: String
        get() = "style generator"

    @Composable
    override fun Content(
        viewModel: MisMatchViewModel, navController: NavController,
        activity: Activity, cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>
    ) {
        var context = LocalContext.current
        StyleScreen(viewModel = viewModel, onStyleClicked = {
            Toast
            .makeText(context,
                "Change Style",
                Toast.LENGTH_SHORT)
            .show()
        }, onLogoClicked = { navController.navigate("home") }
        )
    }
}
