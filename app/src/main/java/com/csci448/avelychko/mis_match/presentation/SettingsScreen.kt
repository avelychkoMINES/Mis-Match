package com.csci448.avelychko.mis_match.presentation

import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen(){
Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color(red = 88, green = 76, blue = 109)),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Settings", fontSize = 30.sp,
                color = Color(red=226, green=114, blue=91),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold)
        }
        Box(
            //contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(224, 224, 224)).fillMaxSize(),
        ) {
            Column() {
              

        }
    }
}
