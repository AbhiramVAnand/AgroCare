package com.abhiram.agrocare.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.DeviceThermostat
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhiram.agrocare.data.room.DeviceEvent
import com.abhiram.agrocare.data.room.DeviceState
import com.abhiram.agrocare.ui.pages.navigation.LocalNavigator
import com.abhiram.agrocare.ui.pages.navigation.Routes
import com.abhiram.agrocare.ui.theme.FadedBorder
import com.abhiram.agrocare.ui.theme.Secondary
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Carousel (
    state: DeviceState,
    onEvent: (DeviceEvent) -> Unit
) {
    val viewModel = hiltViewModel<MQTTClientViewModel>()
    if (viewModel.showDeviceSheet){
        DeviceBottomSheet()
    }
    Row (
        modifier = Modifier
            .fillMaxWidth(1F)
            .horizontalScroll(rememberScrollState())
    ){
        for (i in state.devices){
            CarouselCardItem(name = i.name, icon = Icons.Rounded.DeviceThermostat, descp = "online")
        }
        CarouselCardItem(name = "Add device", icon = Icons.Rounded.Add, descp = "Click + to add")
    }
}

@Composable
fun CarouselCardItem(name: String, icon : ImageVector, descp : String) {
    val viewModel = hiltViewModel<MQTTClientViewModel>()
    Column(
        modifier = Modifier
            .clickable {
                viewModel.selectedDevice = name
                viewModel.showDeviceSheet = true
            }
            .padding(end = 12.dp)
            .width(180.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Secondary)
            .padding(18.dp)

    ){
        Image(
            imageVector = icon,
            contentDescription = "Icon",
            modifier = Modifier.size(42.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = name,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = descp,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            color = FadedBorder
        )
    }
}