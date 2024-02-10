package com.abhiram.agrocare.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DevicesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addDevice(devices : Devices)

    @Query("SELECT * FROM devices")
    fun getDevices() : List<Devices>

}