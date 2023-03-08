package com.csci448.avelychko.mis_match.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.avelychko.mis_match.R

@Composable
fun OutfitBuilderView() {
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        //LOGO
//        Row() {
//
//        }
        //ARROW & IMAGE
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column() {
                Spacer(modifier = Modifier.height(300.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
                Spacer(modifier = Modifier.height(150.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
                Spacer(modifier = Modifier.height(200.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
            }
            //Image(painter = painterResource(id = ), contentDescription = "")
            Column() {
                Spacer(modifier = Modifier.height(300.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
                Spacer(modifier = Modifier.height(150.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
                Spacer(modifier = Modifier.height(200.dp))
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
            }
        }
        //HEART
        IconButton(modifier = Modifier.padding(5.dp),
            onClick = { /*TODO*/ }) {
            Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24), contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutfitBuilderPreview() {
    OutfitBuilderView()
}