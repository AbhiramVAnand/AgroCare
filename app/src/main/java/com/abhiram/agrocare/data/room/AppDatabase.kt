package com.abhiram.agrocare.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [(Devices::class), (Alerts::class)],
    version = 4
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao() : AppDao

}