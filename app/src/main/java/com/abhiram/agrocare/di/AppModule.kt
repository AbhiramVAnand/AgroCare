package com.abhiram.agrocare.di

import android.content.Context
import androidx.room.Room
import com.abhiram.agrocare.data.repository.DeviceRepository
import com.abhiram.agrocare.data.room.DevicesDao
import com.abhiram.agrocare.data.room.DevicesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesDeviceRepository(devicesDao: DevicesDao) : DeviceRepository{
        return DeviceRepository(devicesDao)
    }

    @Provides
    fun providesDeviceDao(devicesDatabase: DevicesDatabase) : DevicesDao{
        return devicesDatabase.appDao()
    }

    @Provides
    @Singleton
    fun provideDeviceDatabase(@ApplicationContext context: Context) : DevicesDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            DevicesDatabase::class.java,
            "appDb"
        ).build()
    }
}