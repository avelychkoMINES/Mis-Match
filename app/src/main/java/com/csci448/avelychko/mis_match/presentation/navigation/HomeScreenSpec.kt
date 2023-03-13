package com.csci448.avelychko.mis_match.presentation.navigation

object HomeScreenSpec : IScreenSpec {
    override val route: String = "home"

    @Composable
    override fun Content(viewModel: MisMatchViewModel,
                         navController: NavController) {
        HomeScreen(viewModel = quizlerViewModel,
            onXXX = {
            navController.navigate("1")
        }, onYYY =
        {
            navController.navigate("2")
        }, onAAA =
        {
            navController.navigate("3")
        }, onBBB =
        {
            navController.navigate("4")
        })
    }
}
