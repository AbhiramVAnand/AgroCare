package com.abhiram.agrocare.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.abhiram.agrocare.data.room.DevicesDao
import com.abhiram.agrocare.data.room.Devices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DeviceRepository(private val devicesDao : DevicesDao){

    val allDevices = MutableLiveData<List<Devices>>()
    val devices = MutableLiveData<Devices>()


    fun addDevice(newDevices: Devices){
        GlobalScope.launch {
            devicesDao.addDevice(newDevices)
        }
    }

    fun getAllDevice(){
        GlobalScope.launch {
            Log.e("val", devicesDao.getDevices().toString())
        }
    }
}