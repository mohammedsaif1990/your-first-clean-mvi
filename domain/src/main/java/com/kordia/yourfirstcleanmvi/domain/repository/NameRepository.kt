package com.kordia.yourfirstcleanmvi.domain.repository

import com.kordia.yourfirstcleanmvi.domain.entity.NameEntity
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface NameRepository {

    suspend fun getAll(): Flow<DataState<List<NameEntity>>>

    suspend fun insertName(name: NameEntity): Flow<DataState<Long>>

    suspend fun deleteAll()
}