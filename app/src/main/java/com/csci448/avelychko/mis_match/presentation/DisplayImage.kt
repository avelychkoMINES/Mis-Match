import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.csci448.avelychko.mis_match.data.database.Picture

@Composable
fun ImageDisplay(picture: Picture?) {
    if (picture != null) {
        Image(
            painter = painterResource(
                id = picture.imageId
            ), modifier = Modifier.fillMaxSize(), contentDescription = ""
        )
    }
}
