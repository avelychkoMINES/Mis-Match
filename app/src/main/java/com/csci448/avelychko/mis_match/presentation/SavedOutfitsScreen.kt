package com.csci448.avelychko.mis_match.presentation

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SavedOutfitsView(viewModel: PhotographViewModel) {
    val context = LocalContext.current
    val tripletsState by viewModel.getTriplets().collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(224, 224, 224)),
        ) {
            Column() {
                //TITLE
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(red = 88, green = 76, blue = 109)),
                    horizontalArrangement = Arrangement.Center) {
                    Text(text = "Saved Outfits", fontSize = 30.sp,
                        color = Color(red=226, green=114, blue=91),
                        fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.SemiBold)
                }
                //ARROW & IMAGE
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.7f)
                    .fillMaxHeight(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
//                    IconButton(onClick = { Toast.makeText(context, "Shows previous saved outfit", Toast.LENGTH_SHORT).show() }) {
//                        Icon(painter = painterResource(id = R.drawable.baseline_chevron_left_24), contentDescription = "")
//                    }
//                    IconButton(onClick = { Toast.makeText(context, "Shows next saved outfit", Toast.LENGTH_SHORT).show() }) {
//                        Icon(painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "")
//                    }
                    //Log.d("saved", "size: ${viewModel.getTriplets().size}")
//                    LazyRow(modifier = Modifier.padding(10.dp), content = { items(tripletsState) { item ->
//
//
//                        Column{
//                            AsyncImage(model = item.topFile, contentDescription = "",
//                                modifier = Modifier.width(80.dp).height(80.dp))
//                            AsyncImage(model = item.bottomFile, contentDescription = "",
//                                modifier = Modifier.width(80.dp).height(80.dp))
//                            AsyncImage(model = item.shoeFile, contentDescription = "",
//                                modifier = Modifier.width(80.dp).height(80.dp))
//                        }
//                    } })
                    LazyRow(modifier = Modifier.padding(10.dp), content = {
                        items(tripletsState) { item ->
                            Column(modifier = Modifier.fillMaxWidth(0.2f).align(Alignment.CenterVertically),) {
                                val topImageLoaded = remember(item.topFile) {
                                    mutableStateOf(false)
                                }
                                val bottomImageLoaded = remember(item.bottomFile) {
                                    mutableStateOf(false)
                                }
                                val shoeImageLoaded = remember(item.shoeFile) {
                                    mutableStateOf(false)
                                }

                                // Check if the top image file is valid
                                Row(modifier = Modifier.fillMaxHeight(0.2f)) {
                                    AsyncImage(
                                        model = item.topFile,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxWidth(0.4f)
                                            .aspectRatio(1f)
                                            .clip(RectangleShape)
                                            .height(80.dp)
                                            .width(80.dp)
                                            .padding(5.dp),
                                        onLoading = { topImageLoaded.value = true },
                                        onError = { topImageLoaded.value = false }
                                    )
                                }


                                // Check if the bottom image file is valid
                                Row(modifier = Modifier.fillMaxHeight(0.3f)) {
                                    AsyncImage(
                                        model = item.bottomFile,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxWidth(0.4f)
                                            .aspectRatio(0.8f)
                                            .clip(RectangleShape)
                                            .height(100.dp)
                                            .width(80.dp)
                                            .padding(5.dp),
                                        onLoading = { bottomImageLoaded.value = true },
                                        onError = { bottomImageLoaded.value = false }
                                    )
                                }


                                // Check if the shoe image file is valid
                                Row(modifier = Modifier.fillMaxHeight(0.2f)) {
                                    AsyncImage(
                                        model = item.shoeFile,
                                        contentDescription = "",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.fillMaxWidth(0.3f)
                                            .aspectRatio(2f)
                                            .clip(RectangleShape)
                                            .height(40.dp)
                                            .width(80.dp)
                                            .padding(5.dp),
                                        onLoading = { shoeImageLoaded.value = true },
                                        onError = { shoeImageLoaded.value = false }
                                    )
                                }


                                Log.d("saved", "top loaded: ${topImageLoaded.value} bottom loaded: ${bottomImageLoaded.value} shoe loaded: ${shoeImageLoaded.value}")

                                // Delete the triplet if at least one image file is not valid
                                if (!(topImageLoaded.value && bottomImageLoaded.value && shoeImageLoaded.value)) {
                                    viewModel.deleteTriplet(item)
                                }
                            }
                        }
                    })
                }
//                //HEART
//                IconButton(modifier = Modifier.padding(5.dp),
//                    onClick = { Toast.makeText(context, "Removes from saved outfits", Toast.LENGTH_SHORT).show() }) {
//                    Icon(painter = painterResource(id = R.drawable.baseline_favorite_24), contentDescription = "",
//                    tint = Color.Red)
//                }
            }

        }

    }
}

//@Preview(showBackground = true)
//@Composable
//fun SavedOutfitsPreview() {
//    SavedOutfitsView() {}
//}
