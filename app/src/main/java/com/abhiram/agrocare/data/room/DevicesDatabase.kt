package com.abhiram.agrocare.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [(Devices::class)], version = 1, exportSchema = false)
abstract class DevicesDatabase : RoomDatabase() {
    abstract fun appDao() : DevicesDao

    @Volatile
    private var INSTANCE : DevicesDatabase? = null

    fun getInstance(
        context: Context
    ): DevicesDatabase {
        synchronized(this){
            var instance = INSTANCE

            if (instance==null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    DevicesDatabase::class.java,
                    "app_db"
                ).fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
            }
            return instance
        }
    }
}