package com.abhiram.agrocare.data.room

data class AlertState(
    val alerts : List<Alerts> = emptyList(),
    val msg : String = "",
    val time : String = ""
)
