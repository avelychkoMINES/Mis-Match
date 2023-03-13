package com.csci448.avelychko.mis_match

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetView(onLogoClicked: () -> Unit) {
    //clothes: List<Clothes>
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(title = {
            Text("Mis-Match!",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
                fontFamily = FontFamily.Serif) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color(red = 199, green = 173, blue = 127)),
            modifier = Modifier.clickable { onLogoClicked() }
        )
        Divider(thickness = 2.dp, color = Color.Black)

        Text(text = "Tops")
        Row(modifier = Modifier
            .padding(10.dp)) {
            //items(clothes) { Image(painter = it)}
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
        }
        Text(text = "Bottoms")
        Row(modifier = Modifier
            .padding(10.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
        }
        Text(text = "Shoes")
        Row(modifier = Modifier
            .padding(10.dp)) {
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
            Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClosetPreview() {
    ClosetView()
}