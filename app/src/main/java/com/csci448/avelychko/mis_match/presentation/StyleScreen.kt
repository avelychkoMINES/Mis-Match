import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.avelychko.mis_match.presentation.viewmodel.MisMatchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StyleScreen (viewModel: MisMatchViewModel, onStyleClicked: (String) -> Unit, onLogoClicked: () -> Unit) : Unit {
    val currentContext = LocalContext.current

    Column() {
        CenterAlignedTopAppBar(title = {Text("Mis-Match!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
            fontFamily = FontFamily.Serif) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color(red = 199, green = 173, blue = 127)),
            modifier = Modifier.clickable { onLogoClicked() }
        )
        Divider(thickness = 2.dp, color = Color.Black)


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth()
                .background(color = Color(red = 225, green = 208, blue = 191)),
        ) {

        }
    }
}
