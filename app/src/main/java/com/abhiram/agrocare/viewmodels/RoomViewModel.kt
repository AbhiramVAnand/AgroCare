package com.abhiram.agrocare.viewmodels

import androidx.lifecycle.ViewModel
import com.abhiram.agrocare.data.repository.DeviceRepository
import com.abhiram.agrocare.data.room.Devices
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor (private val deviceRepository : DeviceRepository) : ViewModel(){
    fun addDevice(device : Devices){
        deviceRepository.addDevice(device)
    }

    fun getDevices(){
        deviceRepository.getAllDevice()
    }
}