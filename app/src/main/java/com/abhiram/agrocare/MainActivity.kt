package com.abhiram.agrocare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.room.Room
import com.abhiram.agrocare.data.repository.PreferencesRepo
import com.abhiram.agrocare.ui.pages.StartUpPage
import com.abhiram.agrocare.ui.pages.navigation.AppNavHost
import com.abhiram.agrocare.ui.pages.navigation.Routes
import com.abhiram.agrocare.ui.theme.AgroCareTheme
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel
import com.abhiram.agrocare.viewmodels.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgroCareTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val viewModel = hiltViewModel<MQTTClientViewModel>()
                    val isfirst = viewModel.getIsFirst()
                    if (isfirst){
                        AppNavHost(startDestination = Routes.StartUpPage.route)
                    }else{
                        AppNavHost(startDestination = Routes.HomePage.route)
                    }

                }
            }
        }
    }
}
