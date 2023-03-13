package com.csci448.avelychko.mis_match

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(red = 225, green = 208, blue = 191)),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Column(Modifier.padding(vertical = 40.dp)) {
                    Text(text = "Tops",
                        fontSize = 24.sp)
                    Row(modifier = Modifier
                        .padding(10.dp),
                    ) {
                        //items(clothes) { Image(painter = it)}
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                    }
                }
                Column(Modifier.padding(vertical = 40.dp)) {
                    Text(text = "Bottoms",
                        fontSize = 24.sp)
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                    }
                }

                Column(Modifier.padding(vertical = 40.dp)) {
                    Text(text = "Shoes",
                        fontSize = 24.sp)
                    Row(modifier = Modifier
                        .padding(10.dp)) {
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                        Icon(painter = painterResource(id = R.drawable.baseline_circle_25), contentDescription = "")
                    }
                }

            }

        }


    }
}

@Preview(showBackground = true)
@Composable
fun ClosetPreview() {
    ClosetView() {}
}
