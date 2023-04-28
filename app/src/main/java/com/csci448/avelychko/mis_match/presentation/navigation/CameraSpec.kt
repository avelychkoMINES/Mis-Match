package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.CameraView
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility

object CameraSpec: IScreenSpec {
    override val route: String
        get() = "camera"

    @Composable
    override fun Content(
        viewModel: PhotographViewModel, navController: NavController,
        activity: Activity,
        cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>
    ) {
        CameraView()
    }
}