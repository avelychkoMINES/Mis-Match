package com.csci448.avelychko.mis_match.presentation.navigation

import androidx.compose.runtime.Composable
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel

sealed interface IScreenSpec {
    companion object {
        val allScreens = IScreenSpec::class.sealedSubclasses.map { it.objectInstance }
        val root = "mismatch"
        val startDestination = HomeScreenSpec.route
    }

    val route: String

    @Composable
    fun Content(viewModel: MisMatchViewModel, navController: NavController)
}