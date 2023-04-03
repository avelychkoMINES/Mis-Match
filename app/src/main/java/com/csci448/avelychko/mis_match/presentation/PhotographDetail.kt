package edu.mines.csci448.examples.camera.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import edu.mines.csci448.examples.camera.data.Photograph
import java.io.File

@Composable
fun PhotographDetail(photograph: Photograph?) {
    if(photograph != null) {
        val context = LocalContext.current
        // TODO #4 display photo from Uri with AsyncImage
    }
}