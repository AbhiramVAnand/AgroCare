package com.abhiram.agrocare.ui.pages.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.abhiram.agrocare.ui.pages.HomePage
import com.abhiram.agrocare.ui.pages.LoginPage
import com.abhiram.agrocare.ui.pages.StartUpPage
import com.abhiram.agrocare.viewmodels.MQTTClientViewModel


val LocalNavigator = compositionLocalOf <NavHostController>{
    error("error")
}
@Composable
fun AppNavHost(
    navHostController: NavHostController = rememberNavController(),
    startDestination: String,
    viewModel: MQTTClientViewModel
) {
    CompositionLocalProvider(
        LocalNavigator provides navHostController
    ) {
        NavHost(navController = navHostController, startDestination = startDestination){
            composable(Routes.StartUpPage.route){
                StartUpPage()
            }
            composable(Routes.LoginPage.route){
                LoginPage(viewModel)
            }
            composable(Routes.HomePage.route){
                HomePage(viewModel)
            }
        }
    }
}