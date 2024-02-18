package com.abhiram.agrocare.ui.pages.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.abhiram.agrocare.ui.pages.DeviceBottomSheet
import com.abhiram.agrocare.ui.pages.HomePage
import com.abhiram.agrocare.ui.pages.LoginPage
import com.abhiram.agrocare.ui.pages.StartUpPage
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel
import com.abhiram.agrocare.viewmodels.RoomViewModel


val LocalNavigator = compositionLocalOf <NavHostController>{
    error("error")
}
@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String,
) {
    val viewModel = hiltViewModel<RoomViewModel>()
    val state by viewModel.state.collectAsState()
    CompositionLocalProvider(
        LocalNavigator provides navHostController
    ) {
        NavHost(navController = navHostController, startDestination = startDestination){
            composable(Routes.StartUpPage.route){
                StartUpPage()
            }
            composable(Routes.LoginPage.route){
                LoginPage()
            }
            composable(Routes.HomePage.route){
                HomePage(state, viewModel::onEvent)
            }
        }
    }
}