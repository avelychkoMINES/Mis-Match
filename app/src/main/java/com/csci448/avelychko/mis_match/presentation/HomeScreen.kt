package com.csci448.avelychko.mis_match.presentation


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (viewModel : PhotographViewModel, onOutfitBuilderClick: () -> Unit,
                onSavedOutfitsClick: () -> Unit,
                onClosetClick: () -> Unit, onCameraClick: () -> Unit,
                onSettingsClick: () -> Unit) {

    Column() {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.8f)
                .background(color = Color(224, 224, 224)),
        ) {
            Column() {
                ElevatedButton(
                    onClick = { onOutfitBuilderClick() },
                    shape = CutCornerShape(0),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.2f)
                        //.padding(20.dp)
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                        .clip(RoundedCornerShape(0.dp))

                ) {
                    Icon(painter = painterResource(id = R.drawable.t_shirt_svgrepo_com), contentDescription = "Outfit Builder",
                        modifier = Modifier.width(40.dp).height(40.dp).padding(5.dp), tint = Color(red=226, green=114, blue=91))

                    Text("Outfit Builder",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = Color(red=226, green=114, blue=91))
                }
                ElevatedButton(
                    onClick = { onSavedOutfitsClick() },
                    shape = CutCornerShape(0),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.25f)
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                        .clip(RoundedCornerShape(0.dp))

                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_favorite_border_24), contentDescription = "Saved Outfits",
                        modifier = Modifier.width(40.dp).height(40.dp).padding(5.dp), tint = Color(red=226, green=114, blue=91))
                    Text("Saved Outfits",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = Color(red=226, green=114, blue=91))

                }
                ElevatedButton(
                    onClick = { onClosetClick() },
                    shape = CutCornerShape(0),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.35f)
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                        .clip(RoundedCornerShape(0.dp))
                ) {
                    Icon(painter = painterResource(id = R.drawable.clothes_hanger_icon_3), contentDescription = "View Your Closet",
                        modifier = Modifier.width(40.dp).height(40.dp).padding(5.dp), tint = Color(red=226, green=114, blue=91))
                    Text("View Your Closet",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = Color(red=226, green=114, blue=91))
                }

                ElevatedButton(
                    onClick = { onSettingsClick() },
                    shape = CutCornerShape(0),
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .fillMaxHeight(0.35f)
                        .padding(horizontal = 20.dp, vertical = 30.dp)
                        .clip(RoundedCornerShape(0.dp))
                ) {
                    Icon(painter = painterResource(id = R.drawable.clothes_hanger_icon_3), contentDescription = "View Your Closet",
                        modifier = Modifier.width(40.dp).height(40.dp).padding(5.dp), tint = Color(red=226, green=114, blue=91))
                    Text("Settings",
                        fontSize = 28.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        color = Color(red=226, green=114, blue=91))
                }



                }
            IconButton(onClick = { onCameraClick() }, modifier = Modifier.align(Alignment.BottomEnd)) {
                Icon(painter = painterResource(id = R.drawable.baseline_camera_alt_24), contentDescription = "",
                modifier = Modifier.width(100.dp).height(100.dp), tint = Color(red = 88, green = 76, blue = 109))
            }

        }
    }
}

@Preview
@Composable
fun previewHomePage () : Unit {
    //HomeScreen()
}
