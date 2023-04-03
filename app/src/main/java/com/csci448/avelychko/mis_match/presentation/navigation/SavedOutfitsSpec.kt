package com.csci448.avelychko.mis_match.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.SavedOutfitsView
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel

object SavedOutfitsSpec: IScreenSpec {
    override val route: String
        get() = "saved outfits"

    @Composable
    override fun Content(viewModel: MisMatchViewModel, navController: NavController) {
        SavedOutfitsView() { navController.navigate("home") }
    }
}