package com.kordia.yourfirstcleanmvi.di

import android.app.Application
import androidx.room.Room
import com.kordia.yourfirstcleanmvi.data.db.MVIDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * AppModule is to provide di for general used objects
 *
 * @author Mohammedsaif Kordia
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Application): MVIDataBase =
        Room.databaseBuilder(context, MVIDataBase::class.java, "mvi_database")
            .build()
}