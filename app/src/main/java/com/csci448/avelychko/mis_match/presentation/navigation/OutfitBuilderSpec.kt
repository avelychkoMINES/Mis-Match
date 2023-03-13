package com.csci448.avelychko.mis_match.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.ui.theme.OutfitBuilderView

object OutfitBuilderSpec: IScreenSpec {
    override val route: String
        get() = "outfit builder"

    @Composable
    override fun Content(viewModel: MisMatchViewModel, navController: NavController) {
        OutfitBuilderView() { navController.navigate("home") }
    }
}