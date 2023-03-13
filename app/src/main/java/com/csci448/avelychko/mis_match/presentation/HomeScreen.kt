package com.csci448.mis_match_start.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.avelychko.mis_match.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen () : Unit {

    Column(modifier = Modifier
        .padding(10.dp)) {
        CenterAlignedTopAppBar(title = {Text("Mis-Match!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
            fontFamily = FontFamily.Serif) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(Color(red = 199, green = 173, blue = 127)),
            )
        Divider(thickness = 2.dp, color = Color.Black)


        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(red = 245, green = 245, blue = 221, alpha = 180)),
        ) {
            Column() {
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    shape = CutCornerShape(10),
                    modifier = Modifier
                        .fillMaxWidth()
                        //.padding(20.dp)
                        .padding(horizontal = 20.dp, vertical = 60.dp)

                ) {
                    Text("Outfit Builder",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light)
                }
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    shape = CutCornerShape(10),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 60.dp)

                ) {
                    Text("Saved Outfits",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light)

                }
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    shape = CutCornerShape(10),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 60.dp)
                ) {
                    Text("Style Generator",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light,
                        color = Color.Blue)
                }
                ElevatedButton(
                    onClick = { /*TODO*/ },
                    shape = CutCornerShape(10),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 60.dp)
                ) {
                    Text("View Your Closet",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Light)
                }
                Row(modifier = Modifier.align(Alignment.End)) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_camera_alt_24), contentDescription = "")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun previewHomePage () : Unit {
    HomeScreen()
}