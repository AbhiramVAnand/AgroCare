package com.abhiram.agrocare.ui.pages.navigation

sealed class Routes(val route : String) {
    object StartUpPage : Routes("startup")
    object LoginPage : Routes("login")
    object HomePage : Routes("home")
    object DevicePage : Routes("devicePage")
}