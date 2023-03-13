package com.csci448.avelychko.mis_match

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
import androidx.compose.ui.unit.sp

@Composable
fun SavedOutfitsView() {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
        //LOGO
//        Row() {
//
//        }
        //TITLE
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Saved Outfits", fontSize = 30.sp)
        }
        //ARROW & IMAGE
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { Toast.makeText(context, "Shows previous saved outfit", Toast.LENGTH_SHORT).show() }) {
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
            }
            IconButton(onClick = { Toast.makeText(context, "Shows next saved outfit", Toast.LENGTH_SHORT).show() }) {
                Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
            }
        }
        //HEART
        IconButton(modifier = Modifier.padding(5.dp),
            onClick = { Toast.makeText(context, "Removes from saved outfits", Toast.LENGTH_SHORT).show() }) {
            Icon(painter = painterResource(id = R.drawable.baseline_favorite_24), contentDescription = "")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SavedOutfitsPreview() {
    SavedOutfitsView()
}