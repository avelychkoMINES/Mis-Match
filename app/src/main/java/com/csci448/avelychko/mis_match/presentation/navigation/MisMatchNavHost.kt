import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.csci448.avelychko.mis_match.presentation.navigation.IScreenSpec
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel

@Composable
fun MisMatchNavHost(navController: NavHostController, viewModel: MisMatchViewModel) {
    NavHost( navController = navController, startDestination = IScreenSpec.root ) {
        navigation(IScreenSpec.startDestination, IScreenSpec.root) {
            IScreenSpec.allScreens.forEach { screen ->
                if(screen != null) {
                    composable(route = screen.route, ) {
                        screen.Content(
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }
            }
        }

    }
}
