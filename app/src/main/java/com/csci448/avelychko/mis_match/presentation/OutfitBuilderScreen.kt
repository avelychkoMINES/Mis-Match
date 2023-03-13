package com.csci448.avelychko.mis_match.ui.theme

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.avelychko.mis_match.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutfitBuilderView(onLogoClicked: () -> Unit) {
    val context = LocalContext.current

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {
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