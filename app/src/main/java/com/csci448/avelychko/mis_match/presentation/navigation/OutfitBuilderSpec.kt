package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import android.content.Context
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.NotificationReceiver
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.presentation.OutfitBuilderView
import com.csci448.avelychko.mis_match.util.CameraUtility
import kotlinx.coroutines.CoroutineScope

object OutfitBuilderSpec: IScreenSpec {
    override val route: String
        get() = "outfit builder"
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
        coroutineScope: CoroutineScope
    ) {
        OutfitBuilderView(viewModel, onLogoClicked = { navController.navigate("home")},
        onRandomizeClick = {viewModel.selectedTopState.value = viewModel.getTopPhoto().random()
            viewModel.selectedBottomState.value = viewModel.getBottomPhoto().random()
            viewModel.selectedShoeState.value = viewModel.getShoePhoto().random()})
    }
}