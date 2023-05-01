package com.csci448.avelychko.mis_match.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csci448.avelychko.mis_match.R
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OutfitBuilderView(viewModel: PhotographViewModel, onLogoClicked: () -> Unit,
                      onRandomizeClick: () -> Unit) {
    val context = LocalContext.current

    Column() {
        CenterAlignedTopAppBar(title = {Text("Mis-Match!",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 36.sp,
            fontFamily = FontFamily.Serif)},
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
            //ARROW & IMAGE
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween) {
                Box() {
                    Column() {
                        Row(modifier = Modifier.fillMaxHeight(0.3f).fillMaxWidth()) {
                            IconButton(onClick = {
                                viewModel.moveToPrevTop()
                                Toast.makeText(context, "Shows previous top", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                                    contentDescription = ""
                                )
                            }
                                Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                                    ImageDisplay(viewModel.selectedTopState.value)
                                }
                                IconButton(onClick = {
                                    viewModel.moveToNextTop()
                                    Toast.makeText(context, "Shows next top", Toast.LENGTH_SHORT)
                                        .show()
                                }) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                                        contentDescription = ""
                                    )
                                }
                            }

                        Row(modifier = Modifier.fillMaxHeight(0.4f).fillMaxWidth()) {
                            IconButton(onClick = {
                                viewModel.moveToPrevBottom()
                                Toast.makeText(
                                    context,
                                    "Shows previous bottoms",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                                    contentDescription = ""
                                )
                            }
                            Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                                ImageDisplay(viewModel.selectedBottomState.value)
                            }

                            IconButton(onClick = {
                                viewModel.moveToNextBottom()
                                Toast.makeText(context, "Shows next bottoms", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_chevron_right_24),
                                    contentDescription = "",
                                )
                            }
                        }
                        Row(modifier = Modifier.fillMaxHeight(0.3f).fillMaxWidth()) {
                            IconButton(onClick = {
                                viewModel.moveToPrevShoe()
                                Toast.makeText(context, "Shows previous shoes", Toast.LENGTH_SHORT)
                                    .show()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.baseline_chevron_left_24),
                                    contentDescription = ""
                                )
                            }
                            Box(modifier = Modifier.fillMaxWidth(0.8f)) {
                                ImageDisplay(viewModel.selectedShoeState.value)
                            }

                            IconButton(onClick = {
                                viewModel.moveToNextShoe()
                                Toast.makeText(context, "Shows next shoes", Toast.LENGTH_SHORT)
                                    .show()
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
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .background(color = Color(red = 225, green = 208, blue = 191)),
            Arrangement.SpaceBetween
        ) {
            IconButton(modifier = Modifier.padding(5.dp),
                onClick = {
                    Toast.makeText(context, "Adds to saved outfits", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                    contentDescription = "",
                    tint = Color.Red
                )
            }
            Button(onClick = { onRandomizeClick() }) {
                Text(text = "Randomize Outfit")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun OutfitBuilderPreview() {
//    OutfitBuilderView() {}
//}
