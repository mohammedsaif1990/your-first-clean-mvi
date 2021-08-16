package com.kordia.yourfirstcleanmvi.di

import com.kordia.yourfirstcleanmvi.data.repository.NameRepositoryImpl
import com.kordia.yourfirstcleanmvi.domain.repository.NameRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * RepositoryModule is to bind di for all repositories
 *
 * @author Mohammedsaif Kordia
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindNameRepository(nameRepositoryImpl: NameRepositoryImpl): NameRepository
}