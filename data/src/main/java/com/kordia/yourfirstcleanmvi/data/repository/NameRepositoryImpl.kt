package com.kordia.yourfirstcleanmvi.data.repository

import com.kordia.yourfirstcleanmvi.data.db.name.NameDao
import com.kordia.yourfirstcleanmvi.data.mapper.toNameData
import com.kordia.yourfirstcleanmvi.data.mapper.toNameEntityList
import com.kordia.yourfirstcleanmvi.domain.entity.NameEntity
import com.kordia.yourfirstcleanmvi.domain.repository.NameRepository
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NameRepositoryImpl @Inject constructor(
    private val nameDao: NameDao
) : NameRepository {
    override suspend fun getAll() = flow {
        emit(DataState.Loading)
        try {
            val allNames = nameDao.getAll()
            if (allNames.isEmpty()) {
                emit(DataState.Empty)
            } else {
                emit(DataState.Success(allNames.toNameEntityList()))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun insertName(name: NameEntity) = flow {
        emit(DataState.Loading)
        try {
            val nameLong = nameDao.insert(name.toNameData())
            if (nameLong != -1L) {
                emit(DataState.Success(nameLong))
            } else {
                emit(DataState.Error(Exception("Something went wrong!")))
            }
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

    override suspend fun deleteAll() {
        nameDao.deleteAll()
    }
}