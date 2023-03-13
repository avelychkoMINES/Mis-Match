import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun MisMatchNavHost(navController: NavHostController, misMatchViewModel: MisMatchViewModel) {
    NavHost( navController = navController, startDestination = IScreenSpec.root ) {
        navigation(IScreenSpec.startDestination, IScreenSpec.root) {
            IScreenSpec.allScreens.forEach { screen ->
                if(screen != null) {
                    composable(route = screen.route, ) {
                        screen.Content(
                            misMatchViewModel = misMatchViewModel,
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}
