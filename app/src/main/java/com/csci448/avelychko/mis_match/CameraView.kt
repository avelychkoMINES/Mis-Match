package com.csci448.avelychko.mis_match

import android.graphics.Paint.Align
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun CameraView() {
    val context = LocalContext.current

    Column(modifier = Modifier
        .background(Color.Black)
    )
    {
        Spacer(modifier = Modifier.height(700.dp))

        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Avatar", color = Color.White);
            Text(text = "Tops", color = Color.White);
            Text(text = "Bottoms", color = Color.White);
            Text(text = "Shoes", color = Color.White);
        }
        Row(modifier = Modifier.fillMaxSize(),
        ) {
            IconButton(onClick = {  }) {
                Icon(painter = painterResource(id = R.drawable.baseline_circle_24), contentDescription = "")
            }
        }
        
    }
}

@Preview(showBackground = true)
@Composable
fun CameraPreview() {
    CameraView()
}