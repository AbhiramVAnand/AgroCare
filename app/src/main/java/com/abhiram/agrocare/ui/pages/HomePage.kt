package com.abhiram.agrocare.ui.pages

import android.app.Application
import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.CrisisAlert
import androidx.compose.material.icons.rounded.DeviceHub
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhiram.agrocare.MyApp
import com.abhiram.agrocare.R
import com.abhiram.agrocare.data.repository.PreferencesRepo
import com.abhiram.agrocare.data.room.Devices
import com.abhiram.agrocare.ui.pages.navigation.AppNavHost
import com.abhiram.agrocare.ui.pages.navigation.LocalNavigator
import com.abhiram.agrocare.ui.pages.navigation.Routes
import com.abhiram.agrocare.ui.theme.Background
import com.abhiram.agrocare.ui.theme.Primary
import com.abhiram.agrocare.ui.theme.PrimaryVariant
import com.abhiram.agrocare.ui.theme.Secondary
import com.abhiram.agrocare.ui.theme.SecondaryVariant
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel
import com.abhiram.agrocare.viewmodels.RoomViewModel

@Composable
fun HomePage(viewModel: MQTTClientViewModel) {
    val navHostController = LocalNavigator.current
    val isfirst = viewModel.getIsFirst()
    if (isfirst){
        navHostController.popBackStack(route = Routes.HomePage.route, inclusive = false)
        viewModel.setIsNotFirst()
    }
    val userName = viewModel.getUname().capitalize()
    val roomViewModel = hiltViewModel<RoomViewModel>()
    val dummydevice = Devices(
        "device1",
        20,
        20,
        20
    )
    roomViewModel.addDevice(dummydevice)
    roomViewModel.getDevices()
    Box(modifier = Modifier
        .fillMaxSize(1F)
    ){
        Box(
            modifier = Modifier
                .background(Background)
                .fillMaxWidth(1F)
                .padding(24.dp)
                .align(Alignment.TopCenter)
        ){
            Column {
                Text(
                    text = "AgroCare",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    color = Secondary
                )
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    text = "Hello,",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = Secondary
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        text = userName,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Secondary
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                    Image(
                        modifier = Modifier.size(32.dp),
                        painter = painterResource(id = R.drawable.hand_wave),
                        contentDescription = "HandWave"
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(SecondaryVariant)
                .fillMaxWidth(1F)
                .fillMaxHeight(0.77F)
                .padding(24.dp)
                .align(Alignment.BottomCenter)
        ){
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(1F),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.DeviceHub,
                        contentDescription = "Devices",
                        tint = PrimaryVariant,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Running devices",
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .padding(start = 12.dp)
                    )
                }

                Row(
                    
                ) {
                    
                }
                
                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CrisisAlert,
                        contentDescription = "Alert",
                        tint = PrimaryVariant,
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Alerts",
                        color = Color.Black,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .padding(end = 24.dp, bottom = 32.dp)
                .size(64.dp)
                .clip(RoundedCornerShape(120.dp))
                .background(Primary)
                .align(Alignment.BottomEnd)

        ){
            Icon(
                imageVector = Icons.Rounded.Add,
                contentDescription = "AddDevice",
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(0.75F),
                tint = Secondary
            )
        }
    }
}
