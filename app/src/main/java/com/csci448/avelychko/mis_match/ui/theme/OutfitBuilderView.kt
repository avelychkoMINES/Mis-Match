package com.csci448.avelychko.mis_match.ui.theme

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csci448.avelychko.mis_match.R

@Composable
fun OutfitBuilderView() {
    val context = LocalContext.current

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
                IconButton(onClick = { Toast.makeText(context, "Shows previous top", Toast.LENGTH_SHORT).show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
                }
                Spacer(modifier = Modifier.height(150.dp))
                IconButton(onClick = { Toast.makeText(context, "Shows previous bottoms", Toast.LENGTH_SHORT).show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
                }
                Spacer(modifier = Modifier.height(200.dp))
                IconButton(onClick = { Toast.makeText(context, "Shows previous shoes", Toast.LENGTH_SHORT).show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
                }
            }
            Column() {
                Spacer(modifier = Modifier.height(300.dp))
                IconButton(onClick = { Toast.makeText(context, "Shows next top", Toast.LENGTH_SHORT).show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
                }
                Spacer(modifier = Modifier.height(150.dp))
                IconButton(onClick = { Toast.makeText(context, "Shows next bottoms", Toast.LENGTH_SHORT).show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
                }
                Spacer(modifier = Modifier.height(200.dp))
                IconButton(onClick = { Toast.makeText(context, "Shows next shoes", Toast.LENGTH_SHORT).show() }) {
                    Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
                }
            }
        }
        //HEART
        IconButton(modifier = Modifier.padding(5.dp),
            onClick = { Toast.makeText(context, "Adds to saved outfits", Toast.LENGTH_SHORT).show() }) {
            Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24), contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutfitBuilderPreview() {
    OutfitBuilderView()
}