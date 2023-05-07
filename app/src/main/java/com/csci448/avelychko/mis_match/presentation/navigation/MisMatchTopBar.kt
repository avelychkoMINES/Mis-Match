package com.csci448.avelychko.mis_match.presentation.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@Composable
fun MisMatchTopBar (navHostController: NavHostController,
                      photographViewModel: PhotographViewModel,
                      context: Context
) {
    val navBackStackEntryState = navHostController.currentBackStackEntryAsState()
    IScreenSpec.TopBar(
        photographViewModel = photographViewModel,
        navHostController = navHostController,
        navBackStackEntry = navBackStackEntryState.value,
        context = context
    )
}