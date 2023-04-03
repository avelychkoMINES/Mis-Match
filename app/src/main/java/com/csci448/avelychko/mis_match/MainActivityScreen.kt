package edu.mines.csci448.examples.camera.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.mines.csci448.examples.camera.presentation.detail.PhotographDetail
import edu.mines.csci448.examples.camera.presentation.list.PhotographList
import edu.mines.csci448.examples.camera.presentation.viewmodel.PhotographViewModel
import edu.mines.csci448.examples.camera.R

@Composable
fun MainActivityScreen(photographViewModel: PhotographViewModel,
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
                    text = stringResource(id = R.string.button_label_launch_intent)
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