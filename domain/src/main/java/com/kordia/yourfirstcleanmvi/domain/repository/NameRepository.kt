package com.kordia.yourfirstcleanmvi.domain.repository

import com.kordia.yourfirstcleanmvi.domain.model.NameDto
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface NameRepository {

    suspend fun getAll(): Flow<DataState<List<NameDto>>>

    suspend fun insertName(name: NameDto): Flow<DataState<Long>>

    suspend fun deleteAll()
}