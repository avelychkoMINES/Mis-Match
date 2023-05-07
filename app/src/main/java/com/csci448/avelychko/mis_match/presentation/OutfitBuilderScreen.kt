package com.csci448.avelychko.mis_match.presentation

import android.media.MediaPlayer
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.data.Triplet
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutfitBuilderView(viewModel: PhotographViewModel, onLogoClicked: () -> Unit,
                      onRandomizeClick: () -> Unit) {
    val context = LocalContext.current
    val mMediaPlayer = MediaPlayer.create(context, R.raw.audio)

    if (viewModel.getTopPhoto().isNotEmpty() && viewModel.getBottomPhoto().isNotEmpty()
        && viewModel.getShoePhoto().isNotEmpty()) {
        viewModel.builderEnabled.value = true
    } else {
        viewModel.builderEnabled.value = false
    }

    Column() {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(224, 224, 224)).fillMaxWidth(),
        ) {
            Column() {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(red = 88, green = 76, blue = 109)),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Outfit Builder", fontSize = 30.sp,
                        color = Color(red = 226, green = 114, blue = 91),
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                //ARROW & IMAGE
                Row(
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight()) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(5.dp),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Row(modifier = Modifier.fillMaxHeight(0.03f)){}
                            Row(modifier = Modifier.fillMaxHeight(0.35f).fillMaxWidth()) {
                                IconButton(onClick = {
                                    if (viewModel.builderEnabled.value) {
                                        viewModel.moveToPrevTop()
                                        mMediaPlayer.start()
                                    } else {
                                        Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                                    }

                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                                        contentDescription = ""
                                    )
                                }
                                Box(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    //ImageDisplay(viewModel.selectedTopState.value)
                                    if (viewModel.selectedTopState.value != null) {
                                        AsyncImage(
                                            model = viewModel.selectedTopState.value!!.photographFileName,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxWidth(0.4f)
                                                    .aspectRatio(1f)
                                                    .clip(RectangleShape)
                                        )
                                    }
                                }
                                IconButton(onClick = {
                                    if (viewModel.builderEnabled.value) {
                                        viewModel.moveToNextTop()
                                        mMediaPlayer.start()
                                    } else {
                                        Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                                    }

                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                                        contentDescription = ""
                                    )
                                }
                            }

                            Row(modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth()) {
                                IconButton(onClick = {
                                    if (viewModel.builderEnabled.value) {
                                        viewModel.moveToPrevBottom()
                                        mMediaPlayer.start()
                                    } else {
                                        Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                                        contentDescription = ""
                                    )
                                }
                                Box(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (viewModel.selectedBottomState.value != null) {
                                        AsyncImage(
                                            model = viewModel.selectedBottomState.value!!.photographFileName,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxWidth(0.4f)
                                                .aspectRatio(0.8f)
                                                .clip(RectangleShape)
                                        )
                                    }
                                }

                                IconButton(onClick = {
                                    if (viewModel.builderEnabled.value) {
                                        viewModel.moveToNextBottom()
                                        mMediaPlayer.start()
                                    } else {
                                        Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                                        contentDescription = "",
                                    )
                                }
                            }
                            Row(modifier = Modifier.fillMaxHeight(0.55f).fillMaxWidth()) {
                                IconButton(onClick = {
                                    if (viewModel.builderEnabled.value) {
                                        viewModel.moveToPrevShoe()
                                        mMediaPlayer.start()
                                    } else {
                                        Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                                        contentDescription = ""
                                    )
                                }
                                Box(
                                    modifier = Modifier.fillMaxWidth(0.9f),
                                    contentAlignment = Alignment.Center
                                ) {
                                    if (viewModel.selectedShoeState.value != null) {
                                        AsyncImage(
                                            model = viewModel.selectedShoeState.value!!.photographFileName,
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxWidth(0.4f)
                                                .aspectRatio(2f)
                                                .clip(RectangleShape)
                                        )
                                    }
                                }

                                IconButton(onClick = {
                                    if (viewModel.builderEnabled.value) {
                                        viewModel.moveToNextShoe()
                                        mMediaPlayer.start()
                                    } else {
                                        Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                                    }
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                                        contentDescription = ""
                                    )
                                }
                            }
                        }
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .background(color = Color(224, 224, 224)),
                    Arrangement.SpaceBetween
                ) {
                    ElevatedButton(
                        onClick = { if (viewModel.selectedTopState.value != null && viewModel.selectedBottomState.value != null &&
                                viewModel.selectedShoeState.value != null) {
                            val newOutfit = Triplet(topFile = viewModel.selectedTopState.value!!.photographFileName,
                                bottomFile = viewModel.selectedBottomState.value!!.photographFileName, shoeFile = viewModel.selectedShoeState.value!!.photographFileName)

                            viewModel.addTriplet(newOutfit)
                        } else {
                            Toast.makeText(context, "You need to select a top, bottom, and shoe!", Toast.LENGTH_SHORT).show()
                        } },
                        shape = CutCornerShape(0),
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .clip(RoundedCornerShape(0.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                            contentDescription = "",
                            tint = Color(red = 226, green = 114, blue = 91)
                        )
                        Text(
                            "Save Outfit",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Light,
                            color = Color(red = 226, green = 114, blue = 91)
                        )
                    }
                    ElevatedButton(
                        onClick = { if (viewModel.builderEnabled.value) {
                            onRandomizeClick()
                        } else {
                            Toast.makeText(context, "Save more clothes first!", Toast.LENGTH_SHORT).show()
                        } },
                        shape = CutCornerShape(0),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 20.dp, vertical = 30.dp)
                            .clip(RoundedCornerShape(0.dp))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_shuffle_24),
                            contentDescription = "Randomize Outfit",
                            tint = Color(red = 226, green = 114, blue = 91)
                        )
                        Text(
                            "Randomize Outfit",
                            fontSize = 24.sp,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Light,
                            color = Color(red = 226, green = 114, blue = 91)
                        )
                    }
                }
            }



//            IconButton(modifier = Modifier.padding(5.dp),
//                onClick = {
//                    Toast.makeText(context, "Adds to saved outfits", Toast.LENGTH_SHORT).show()
//                }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
//                    contentDescription = "",
//                    tint = Color.Red
//                )
//            }
//            Button(onClick = { onRandomizeClick() }) {
//                Text(text = "Randomize Outfit")
//            }
        }
    }
}
//@Preview(showBackground = true)
//@Composable
//fun OutfitBuilderPreview() {
//    OutfitBuilderView() {}
//}
