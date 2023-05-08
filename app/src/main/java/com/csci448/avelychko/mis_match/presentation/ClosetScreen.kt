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
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel
import kotlinx.coroutines.launch
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClosetView(viewModel: PhotographViewModel, onLogoClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color(red = 88, green = 76, blue = 109)),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "My Closet", fontSize = 30.sp,
                color = Color(red=226, green=114, blue=91),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold)
        }
        Box(
            //contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(224, 224, 224)).fillMaxSize(),
        ) {
            Column() {
                Column(Modifier.padding(vertical = 5.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(red=241, green=241, blue=241)),
                        horizontalArrangement = Arrangement.Start) {
                        Text(text = "Tops", fontSize = 26.sp,
                            color = Color(red=226, green=114, blue=91),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(10.dp))
                    }
                    LazyRow(modifier = Modifier.padding(10.dp), content = { items(viewModel.getTopPhoto()) { item ->
                        AsyncImage(model = item.photographFileName, contentDescription = "",
                            modifier = Modifier.width(80.dp).height(80.dp).clickable {
                                deletePhoto(item, viewModel)
                            })
                    } })
                }
                Column(Modifier.padding(vertical = 5.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(red=241, green=241, blue=241)),
                        horizontalArrangement = Arrangement.Start) {
                        Text(text = "Bottoms", fontSize = 26.sp,
                            color = Color(red=226, green=114, blue=91),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(10.dp))
                    }
                    LazyRow(modifier = Modifier.padding(10.dp),content = { items(viewModel.getBottomPhoto()) { item ->
                        AsyncImage(model = item.photographFileName, contentDescription = "",
                            modifier = Modifier.width(80.dp).height(80.dp))
                    } })
                }

                Column(Modifier.padding(vertical = 5.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(red=241, green=241, blue=241)),
                        horizontalArrangement = Arrangement.Start) {
                        Text(text = "Shoes", fontSize = 26.sp,
                            color = Color(red=226, green=114, blue=91),
                            fontFamily = FontFamily.Serif,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.padding(10.dp))
                    }
                    LazyRow(modifier = Modifier.padding(10.dp), content = { items(viewModel.getShoePhoto()) { item ->
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

fun deletePhoto(photograph: Photograph, viewModel: PhotographViewModel) {
    val file = File(photograph.photographFileName)
    if (file.exists()) {
        file.delete()
        viewModel.deletePhotograph(photograph)

        // Trigger recomposition manually
        //viewModel.refresh()
    // Perform necessary operations to update the database or list of photos
    }

}