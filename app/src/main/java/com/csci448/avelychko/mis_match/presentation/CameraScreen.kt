package com.csci448.avelychko.mis_match

import SimpleCameraPreview
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@Composable
fun CameraView() {
    val context = LocalContext.current

    Box {
        SimpleCameraPreview()
    Column(modifier = Modifier
        .fillMaxHeight()
    )
    {
        Box(modifier = Modifier.weight(0.8f)) {

        }
        Box(modifier = Modifier.weight(0.2f)

            .background(Color.Black)) {
            Column(Modifier.padding(vertical = 30.dp)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Avatar",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.clickable {
                            Toast.makeText(
                                context,
                                "Taking avatar pic",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        });
                    Text(text = "Tops",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.clickable {
                            Toast.makeText(
                                context,
                                "Taking top pic",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        });
                    Text(text = "Bottoms",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.clickable {
                            Toast.makeText(
                                context,
                                "Taking bottoms pic",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        });
                    Text(text = "Shoes",
                        color = Color.White,
                        fontSize = 24.sp,
                        modifier = Modifier.clickable {
                            Toast.makeText(
                                context,
                                "Taking shoes pic",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                        });
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(onClick = {
                        Toast.makeText(context, "Takes a picture", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_circle_24),
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }

    }
}

@Preview(showBackground = true)
@Composable
fun CameraPreview() {
    CameraView()
}
