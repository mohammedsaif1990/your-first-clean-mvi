package com.kordia.yourfirstcleanmvi.di

import com.kordia.yourfirstcleanmvi.data.db.MVIDataBase
import com.kordia.yourfirstcleanmvi.data.db.name.NameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * DaoModule is to provide di for Room DAOs interfaces
 *
 * @author Mohammedsaif Kordia
 */
@Module
@InstallIn(SingletonComponent::class)
class DaoModule {

    @Provides
    fun provideNameDao(mviDatabase: MVIDataBase): NameDao =
        mviDatabase.nameDao()
}