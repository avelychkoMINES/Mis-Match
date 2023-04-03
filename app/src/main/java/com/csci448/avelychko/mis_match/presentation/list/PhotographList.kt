package com.csci448.avelychko.mis_match.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.csci448.avelychko.mis_match.data.Photograph
import com.csci448.avelychko.mis_match.R

@Composable
fun PhotographList(
    photographs: List<Photograph>,
    onSelectPhotograph: (selectedPhotograph: Photograph) -> Unit,
    onDeletePhotograph: (photographToDelete: Photograph) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(photographs) { photograph ->
            PhotographRow(
                photograph = photograph,
                onSelectPhotograph = onSelectPhotograph,
                onDeletePhotograph = onDeletePhotograph
            )
        }
    }
}

@Composable
private fun PhotographRow(
    photograph: Photograph,
    onSelectPhotograph: (selectedPhotograph: Photograph) -> Unit,
    onDeletePhotograph: (photographToDelete: Photograph) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onSelectPhotograph(photograph) },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = photograph.photographFileName)
            IconButton(
                onClick = { onDeletePhotograph(photograph) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Delete,
                    contentDescription = ""
                )
            }
        }
    }
}