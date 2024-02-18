package com.abhiram.agrocare.data.room

data class DeviceState(
    val devices: List<Devices> = emptyList(),
    var name: String = "",
    var plantName: String = "",
    var moisture: String = "",
    val isMotorRunning: Boolean = false,
    val isAddingDevice: Boolean = false
)
