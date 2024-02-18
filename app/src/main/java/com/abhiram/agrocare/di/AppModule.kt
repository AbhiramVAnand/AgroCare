package com.abhiram.agrocare.di

import android.content.Context
import androidx.room.Room
import com.abhiram.agrocare.data.room.AppDao
import com.abhiram.agrocare.data.room.AppDatabase
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
    fun providesDeviceDao(appDatabase: AppDatabase) : AppDao{
        return appDatabase.appDao()
    }

    @Provides
    @Singleton
    fun provideDeviceDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "appDb"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


}