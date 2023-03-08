package com.csci448.avelychko.mis_match

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ClosetView() {
    //clothes: List<Clothes>
    Column(modifier = Modifier.fillMaxSize()) {
        //LOGO
//        Row() {
//
//        }
        Text(text = "Tops")
        Row(modifier = Modifier
            .padding(10.dp)) {
            //items(clothes) { Image(painter = it)}
        }
        Text(text = "Bottoms")
        Row(modifier = Modifier
            .padding(10.dp)) {

        }
        Text(text = "Shoes")
        Row(modifier = Modifier
            .padding(10.dp)) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClosetPreview() {
    ClosetView()
}