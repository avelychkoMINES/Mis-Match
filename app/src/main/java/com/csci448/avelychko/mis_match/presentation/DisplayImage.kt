import androidx.compose.foundation.Image

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
