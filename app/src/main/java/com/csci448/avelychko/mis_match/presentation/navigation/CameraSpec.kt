package com.csci448.avelychko.mis_match.presentation.navigation

import SimpleCameraPreview
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.CameraView
import com.csci448.avelychko.mis_match.ClosetView
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel

object CameraSpec: IScreenSpec {
    override val route: String
        get() = "camera"

    @Composable
    override fun Content(viewModel: MisMatchViewModel, navController: NavController) {
        SimpleCameraPreview()
    }
}