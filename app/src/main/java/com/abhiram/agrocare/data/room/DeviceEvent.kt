package com.abhiram.agrocare.data.room


sealed interface DeviceEvent {
    object AddDevice : DeviceEvent
    data class SetName(val name : String) : DeviceEvent
    data class SetMoisture(val moisture: String) : DeviceEvent
    data class SetMotorRunning(val motorStatus : Boolean) : DeviceEvent
    data class DeleteDevice(val device : Devices) : DeviceEvent
    object ShowDialog : DeviceEvent
    object HideDialog : DeviceEvent

}