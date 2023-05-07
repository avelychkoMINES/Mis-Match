package com.csci448.avelychko.mis_match.presentation.navigation

import StyleScreen
import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.NotificationReceiver
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.SettingsScreen
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility
import kotlinx.coroutines.CoroutineScope

object SettingsScreenSpec  : IScreenSpec {
    override val route: String
        get() = "settings"
    override val arguments: List<NamedNavArgument> = emptyList()
    override fun buildRoute(vararg args: String?) = SavedOutfitsSpec.route
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
        coroutineScope: CoroutineScope,
        onNotify: () -> Unit
    ) {
        SettingsScreen(viewModel, onNotify)
    }
}
