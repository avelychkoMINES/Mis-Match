package com.csci448.avelychko.mis_match.presentation.navigation

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.camera.core.ImageCapture
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.csci448.avelychko.mis_match.NotificationReceiver
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import com.csci448.avelychko.mis_match.util.CameraUtility
import com.csci448.avelychko.mis_match.R
import kotlinx.coroutines.CoroutineScope

sealed interface IScreenSpec {
    companion object {
        private const val LOG_TAG = "448.IScreenSpec"

        val allScreens = IScreenSpec::class.sealedSubclasses.associate {
            Log.d(LOG_TAG, "allScreens: mapping route \"${it.objectInstance?.route ?: ""}\" to object \"${it.objectInstance}\"")
            it.objectInstance?.route to it.objectInstance
        }
        const val root = "mismatch"
        val startDestination = HomeScreenSpec.route

        @Composable
        fun TopBar(photographViewModel: PhotographViewModel,
                   navHostController: NavHostController,
                   navBackStackEntry: NavBackStackEntry?,
                   context: Context) {
            val route = navBackStackEntry?.destination?.route ?: ""
            allScreens[route]?.TopAppBarContent(photographViewModel,
                navHostController, navBackStackEntry, context)
        }
    }

    val route: String
    val arguments: List<NamedNavArgument>
    fun buildRoute(vararg args: String?): String
    var title: Int

    @Composable
    fun Content(
        viewModel: PhotographViewModel, navController: NavController,
        activity: Activity,
        cameraUtility: CameraUtility,
        permissionLauncher: ActivityResultLauncher<Array<String>>,
        navBackStackEntry: NavBackStackEntry,
        context: Context,
        coroutineScope: CoroutineScope,
        onNotify: () -> Unit
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TopAppBarContent(
        photographViewModel: PhotographViewModel,
        navHostController: NavHostController,
        navBackStackEntry: NavBackStackEntry?,
        context: Context
    ) {
        CenterAlignedTopAppBar (
            navigationIcon = if (navHostController.previousBackStackEntry != null) {
                {
                    IconButton(onClick = { navHostController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.menu_back_desc),
                            tint = Color(red=226, green=114, blue=91)
                        )
                    }
                }
            } else {
                { }
            }, title = {
                Row() {Text("Mis-Match",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                color = Color(224, 224, 224),
                fontSize = 42.sp,
                fontFamily = FontFamily.Serif)
                Text("!",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(red=226, green=114, blue=91),
                    fontSize = 42.sp,
                    fontFamily = FontFamily.Serif)}
            },
            actions = {TopAppBarActions(
                photographViewModel = photographViewModel,
                navHostController = navHostController,
                navBackStackEntry = navBackStackEntry,
                context = context
            )},
            colors = TopAppBarDefaults.topAppBarColors(Color(red = 88, green = 76, blue = 109))
        )

    }

    @Composable
    fun TopAppBarActions(photographViewModel: PhotographViewModel,
                         navHostController: NavHostController,
                         navBackStackEntry: NavBackStackEntry?,
                         context: Context
    ) {

    }
}