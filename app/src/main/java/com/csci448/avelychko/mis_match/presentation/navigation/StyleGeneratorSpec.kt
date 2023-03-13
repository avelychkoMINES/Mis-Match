package com.csci448.avelychko.mis_match.presentation.navigation

import StyleScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel
import com.csci448.avelychko.mis_match.ui.theme.OutfitBuilderView

object StyleGeneratorSpec: IScreenSpec {
    override val route: String
        get() = "style generator"

    @Composable
    override fun Content(viewModel: MisMatchViewModel, navController: NavController) {
        StyleScreen(viewModel = viewModel, onStyleClicked = {
            Toast
            .makeText(LocalContext.current,
                "Change Style",
                Toast.LENGTH_SHORT)
            .show()
        })
    }
}
