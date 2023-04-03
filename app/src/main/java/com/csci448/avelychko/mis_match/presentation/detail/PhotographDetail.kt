package com.csci448.avelychko.mis_match.presentation.detail

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import coil.compose.AsyncImage
import com.csci448.avelychko.mis_match.data.Photograph
import java.io.File

@Composable
fun PhotographDetail(photograph: Photograph?) {
    if(photograph != null) {
        val context = LocalContext.current
//        AsyncImage(
//            model = photoUri,
//            contentDescription = photoName
//        )
    }
}