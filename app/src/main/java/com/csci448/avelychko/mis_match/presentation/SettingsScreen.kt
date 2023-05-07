package com.csci448.avelychko.mis_match.presentation

import android.app.Activity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.csci448.avelychko.mis_match.NotificationReceiver
import com.csci448.avelychko.mis_match.presentation.viewmodel.PhotographViewModel

@Composable
fun SettingsScreen(viewModel: PhotographViewModel, onNotify: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(red = 88, green = 76, blue = 109)),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Settings", fontSize = 30.sp,
                color = Color(red = 226, green = 114, blue = 91),
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold
            )
        }
        Box(
            //contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(0.8f)
                .background(color = Color(224, 224, 224))
                .fillMaxSize(),
        ) {
            Column() {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(text = "Notifications On")
                    Switch(checked = viewModel.notificationsOn.value,
                        onCheckedChange = {
                            viewModel.setNotification(it)
                            if (it) {
                                onNotify()
                            }
                    })
                }
                Button(onClick = { onNotify() }) {
                }
            }
        }
    }
}
