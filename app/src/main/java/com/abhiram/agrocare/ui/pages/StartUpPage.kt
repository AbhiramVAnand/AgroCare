package com.abhiram.agrocare.ui.pages

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.abhiram.agrocare.R
import com.abhiram.agrocare.ui.pages.navigation.LocalNavigator
import com.abhiram.agrocare.ui.pages.navigation.Routes
import com.abhiram.agrocare.ui.theme.Background
import com.abhiram.agrocare.ui.theme.OnBackground
import com.abhiram.agrocare.ui.theme.OnSecondary
import com.abhiram.agrocare.ui.theme.Secondary
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun StartUpPage() {
    val navHostController = LocalNavigator.current
    Box(modifier = Modifier
        .background(Background)
        .fillMaxSize(1F)
    ){
        Column(
            modifier = Modifier.fillMaxWidth(1F).align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.18F))
            Text(
                text = "Welcome to",
                style = MaterialTheme.typography.headlineLarge,
                color = OnBackground

            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "AgroCare",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold,
                color = OnBackground
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.2F))
            Image(
                painter = painterResource(id = R.drawable.handcrop),
                contentDescription = "Image"
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.2F))
            Text(
                text = "Your precision agriculture partner",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.ExtraBold,
                color = OnBackground
            )
        }
        Box(modifier = Modifier
            .padding(vertical = 64.dp, horizontal = 24.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Secondary)
            .fillMaxWidth(1F)
            .align(Alignment.BottomCenter)
            .clickable {
                navHostController.navigate(Routes.LoginPage.route)
            }
        ){
            Text(
                text = "Start Monitoring",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(18.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = OnSecondary,
                fontWeight = FontWeight.SemiBold
            )
        }
    }

}


@Preview
@Composable
fun PreviewStart() {
    StartUpPage()
}