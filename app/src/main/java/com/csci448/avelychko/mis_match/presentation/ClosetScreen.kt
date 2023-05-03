package com.csci448.avelychko.mis_match.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.csci448.avelychko.mis_match.presentation.ImageDisplay
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetView(viewModel: PhotographViewModel, onLogoClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
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

        Box(
            //contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(red = 225, green = 208, blue = 191)).fillMaxSize(),
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Column(Modifier.padding(vertical = 40.dp)) {
                    Text(text = "Tops",
                        fontSize = 24.sp)
                    LazyRow(content = { items(viewModel.getTopPhoto()) { item ->
                        AsyncImage(model = item.photographFileName, contentDescription = "",
                            modifier = Modifier.width(80.dp).height(80.dp))
                    } })
                }
                Column(Modifier.padding(vertical = 40.dp)) {
                    Text(text = "Bottoms",
                        fontSize = 24.sp)
                    LazyRow(content = { items(viewModel.getBottomPhoto()) { item ->
                        AsyncImage(model = item.photographFileName, contentDescription = "",
                            modifier = Modifier.width(80.dp).height(80.dp))
                    } })
                }

                Column(Modifier.padding(vertical = 40.dp)) {
                    Text(text = "Shoes",
                        fontSize = 24.sp)
                    LazyRow(content = { items(viewModel.getShoePhoto()) { item ->
                        AsyncImage(model = item.photographFileName, contentDescription = "",
                            modifier = Modifier.width(80.dp).height(80.dp))
                    } })
                }

            }

        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ClosetPreview() {
//    ClosetView() {}
//}
