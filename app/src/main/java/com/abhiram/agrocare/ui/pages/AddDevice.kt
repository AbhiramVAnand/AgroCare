package com.abhiram.agrocare.ui.pages

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.abhiram.agrocare.data.room.DeviceEvent
import com.abhiram.agrocare.data.room.DeviceState
import com.abhiram.agrocare.data.room.Devices
import com.abhiram.agrocare.ui.theme.Background
import com.abhiram.agrocare.ui.theme.Secondary

@Composable
fun AddDevices(
    state : DeviceState,
    onEvent : (DeviceEvent) -> Unit,
    modifier : Modifier = Modifier
) {
    val context = LocalContext.current
    AlertDialog(
        modifier = modifier,
        onDismissRequest = {
            onEvent(DeviceEvent.HideDialog)
            onEvent(DeviceEvent.SetName(""))
            onEvent(DeviceEvent.SetMoisture(""))
        },
        confirmButton = {  },
        title = {},
        containerColor = Secondary,
        text = {
               Column(
                   horizontalAlignment = Alignment.CenterHorizontally
               ) {
                   Text(
                       text = "Add Device",
                       style = MaterialTheme.typography.headlineMedium,
                       color = Color.Black,
                       fontWeight = FontWeight.SemiBold,
                       modifier = Modifier.padding(bottom = 12.dp)
                   )
                   OutlinedTextField(
                       modifier = Modifier
                           .padding(vertical = 8.dp)
                           .fillMaxWidth(1F)
                           .imePadding(),
                       value = state.name,
                       onValueChange = { onEvent(DeviceEvent.SetName(it)) },
                       label = { Text("Device Name") },
                       colors = TextFieldDefaults.colors(
                           focusedTextColor = Color.Black,
                           unfocusedContainerColor = Color.Transparent,
                           focusedContainerColor = Color.Transparent,
                           focusedIndicatorColor = Color.Black,
                           unfocusedLabelColor = Color.Black,
                           unfocusedIndicatorColor = Color.Black,
                           unfocusedTextColor = Color.Black,
                           unfocusedPlaceholderColor = Color.Black,
                           unfocusedTrailingIconColor = Color.Black,
                           focusedLabelColor = Color.Black,
                           focusedPlaceholderColor = Color.Black,
                           focusedTrailingIconColor = Color.Black
                       )
                   )
                   OutlinedTextField(
                       modifier = Modifier
                           .padding(vertical = 8.dp)
                           .fillMaxWidth(1F)
                           .imePadding(),
                       value = state.moisture.toString(),
                       onValueChange = { onEvent(DeviceEvent.SetMoisture(it))},
                       label = { Text("Minimum Moisture") },
                       colors = TextFieldDefaults.colors(
                           focusedTextColor = Color.Black,
                           unfocusedContainerColor = Color.Transparent,
                           focusedContainerColor = Color.Transparent,
                           focusedIndicatorColor = Color.Black,
                           unfocusedLabelColor = Color.Black,
                           unfocusedIndicatorColor = Color.Black,
                           unfocusedTextColor = Color.Black,
                           unfocusedPlaceholderColor = Color.Black,
                           unfocusedTrailingIconColor = Color.Black,
                           focusedLabelColor = Color.Black,
                           focusedPlaceholderColor = Color.Black,
                           focusedTrailingIconColor = Color.Black
                       )
                   )
                   Spacer(modifier = Modifier.height(8.dp))
                   Row(
                       modifier = Modifier.fillMaxWidth(1F),
                       verticalAlignment = Alignment.CenterVertically,
                       horizontalArrangement = Arrangement.Center
                   ) {
                       Button(
                           onClick = {
                               onEvent(DeviceEvent.HideDialog)
                               onEvent(DeviceEvent.SetName(""))
                               onEvent(DeviceEvent.SetMoisture(""))
                           },
                           modifier = Modifier,
                           colors = ButtonDefaults.buttonColors(containerColor = Background, contentColor = Secondary)
                       ) {
                           Text(
                               text = "Cancel",
                               modifier = Modifier
                                   .padding(vertical = 8.dp),
                               style = MaterialTheme.typography.bodyLarge
                           )
                       }
                       Spacer(modifier= Modifier.width(24.dp))
                       Button(
                           onClick = {
                               val name = state.name
                               val plantName = state.plantName
                               val moisture = state.moisture

                               if (name.isBlank() || moisture.isBlank()){
                                   Toast.makeText(context, "Please enter values!", Toast.LENGTH_SHORT).show()
                               }else{
                                   onEvent(DeviceEvent.AddDevice)
                                   Toast.makeText(context, "Device added!", Toast.LENGTH_SHORT).show()
                                   onEvent(DeviceEvent.SetName(""))
                                   onEvent(DeviceEvent.SetMoisture(""))
                               }
                           },
                           colors = ButtonDefaults.buttonColors(containerColor = Background, contentColor = Secondary)
                       ) {
                           Text(
                               text = "Add",
                               modifier = Modifier
                                   .padding(vertical = 8.dp),
                               style = MaterialTheme.typography.bodyLarge
                           )
                       }
                   }
               }
        }
    )
}

