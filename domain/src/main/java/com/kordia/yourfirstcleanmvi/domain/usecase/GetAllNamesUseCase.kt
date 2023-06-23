package com.kordia.yourfirstcleanmvi.domain.usecase

import com.kordia.yourfirstcleanmvi.domain.entity.NameEntity
import com.kordia.yourfirstcleanmvi.domain.repository.NameRepository
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import com.kordia.yourfirstcleanmvi.domain.utils.FlowableUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNamesUseCase @Inject constructor(
    private val repository: NameRepository
): FlowableUseCase<Unit, List<NameEntity>>() {
    override suspend fun execute(param: Unit): Flow<DataState<List<NameEntity>>> = repository.getAll()
}