package com.abhiram.agrocare.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.MonitorHeart
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhiram.agrocare.R
import com.abhiram.agrocare.data.room.DeviceEvent
import com.abhiram.agrocare.data.room.DeviceState
import com.abhiram.agrocare.ui.pages.navigation.LocalNavigator
import com.abhiram.agrocare.ui.pages.navigation.Routes
import com.abhiram.agrocare.ui.theme.Background
import com.abhiram.agrocare.ui.theme.FadedBorder
import com.abhiram.agrocare.ui.theme.Secondary
import com.abhiram.agrocare.ui.theme.SecondaryVariant
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel
import com.abhiram.agrocare.viewmodels.RoomViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePage(
    state : DeviceState,
    onEvent : (DeviceEvent) -> Unit
) {
    val viewModel = hiltViewModel<MQTTClientViewModel>()
    val navHostController = LocalNavigator.current
    val isfirst = viewModel.getIsFirst()
    if (isfirst){
        navHostController.popBackStack(route = Routes.HomePage.route, inclusive = false)
        viewModel.setIsNotFirst()
    }

    viewModel.SubscribeToAlert()

    val userName = viewModel.getUname().capitalize()
    val alertState by viewModel.alertState.collectAsState()
    val roomViewModel = hiltViewModel<RoomViewModel>()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(DeviceEvent.ShowDialog)
                },
                modifier = Modifier.size(64.dp),
                containerColor = Background
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "AddDevice",
                    tint = Secondary,
                    modifier = Modifier.fillMaxSize(0.6F)
                )
            }
        }
    ) {paddingValues ->
        if(state.isAddingDevice){
            AddDevices(state = state, onEvent = onEvent)
        }
        if (viewModel.logout){
            LogoutAlert()
        }
        Column(modifier = Modifier
            .fillMaxSize(1F)
            .background(Background)
            .padding(paddingValues)
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(1F)
                    .padding(vertical = 32.dp, horizontal = 24.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "AgroCare",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    color = Secondary,
                    modifier = Modifier.weight(1F),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Image(
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            viewModel.logout = true
                        },
                    imageVector = Icons.Rounded.Logout,
                    contentDescription = "Logout",
                    colorFilter = ColorFilter.tint(Secondary)
                )
            }
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(SecondaryVariant)
                    .fillMaxWidth(1F)
                    .padding(24.dp)
                    .weight(2f)
            ){
                Column {
                    Text(
                        text = "Hello,",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = FadedBorder,
                        modifier = Modifier
                            .padding(top = 12.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = userName,
                            style = MaterialTheme.typography.headlineLarge,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Image(
                            modifier = Modifier.size(32.dp),
                            painter = painterResource(id = R.drawable.hand_wave),
                            contentDescription = "HandWave"
                        )
                    }
                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.MonitorHeart,
                            contentDescription = "Running devices",
                            tint = FadedBorder,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Running devices",
                            color = FadedBorder,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))
//                    Carousel
                    Carousel(state = state, onEvent = roomViewModel::onEvent)

                    Spacer(modifier = Modifier.height(32.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.NotificationsNone,
                            contentDescription = "Alert",
                            tint = FadedBorder,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "Alerts",
                            color = FadedBorder,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    ListAlerts(alertState)
                }
            }
        }

    }

}
