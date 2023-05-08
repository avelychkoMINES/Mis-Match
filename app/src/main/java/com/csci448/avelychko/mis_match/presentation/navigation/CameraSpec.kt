package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.NotificationReceiver
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.CameraView
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility
import kotlinx.coroutines.CoroutineScope

object CameraSpec: IScreenSpec {
    override val route: String
        get() = "camera"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = route
    override var title: Int
        get() = R.string.app_name
        set(value) {
            title = value
        }

    @Composable
    override fun Content(
        viewModel: PhotographViewModel, navController: NavController,
        activity: Activity,
        cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope
    ) {
        val selectedCameraState = viewModel.selectedCameraState.collectAsState()
        CameraView()
    }
}