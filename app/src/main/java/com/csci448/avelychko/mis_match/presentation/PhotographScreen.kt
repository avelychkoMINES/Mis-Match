package com.csci448.avelychko.mis_match.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.csci448.avelychko.mis_match.presentation.detail.PhotographDetail
import com.csci448.avelychko.mis_match.presentation.list.PhotographList
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@Composable
fun PhotographScreen(photographViewModel: PhotographViewModel,
                     takePicture: () -> Unit) {
    val photographListState = photographViewModel.photographListStateFlow.collectAsStateWithLifecycle()
    val selectedPhotographState = photographViewModel.currentPhotographStateFlow.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = { takePicture() }
            ) {
                Text(
                    text = ""
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
        ) {
            PhotographList(
                photographs = photographListState.value,
                onSelectPhotograph = { selectedPhotograph ->
                    photographViewModel.loadPhotographById(selectedPhotograph.id)
                },
                onDeletePhotograph = { photographToDelete ->
                    photographViewModel.deletePhotograph(photographToDelete)
                }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            PhotographDetail(
                photograph = selectedPhotographState.value
            )
        }
    }
}