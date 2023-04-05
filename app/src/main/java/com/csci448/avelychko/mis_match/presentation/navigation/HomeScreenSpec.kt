package com.csci448.avelychko.mis_match.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.presentation.HomeScreen

object HomeScreenSpec : IScreenSpec {
    override val route: String = "home"

    @Composable
    override fun Content(viewModel: MisMatchViewModel,
                         navController: NavController
    ) {
        HomeScreen(viewModel = viewModel,
            onOutfitBuilderClick = {
            navController.navigate("outfit builder")
        }, onSavedOutfitsClick =
        {
            navController.navigate("saved outfits")
        }, onStyleGeneratorClick =
        {
            navController.navigate("style generator")
        }, onClosetClick =
        {
            navController.navigate("closet")
        },
        {
            navController.navigate("camera")
        })
    }
}
