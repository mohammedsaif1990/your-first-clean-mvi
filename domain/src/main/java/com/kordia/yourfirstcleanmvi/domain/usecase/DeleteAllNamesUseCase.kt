package com.kordia.yourfirstcleanmvi.domain.usecase

import com.kordia.yourfirstcleanmvi.domain.repository.NameRepository
import com.kordia.yourfirstcleanmvi.domain.utils.BaseUseCase
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import javax.inject.Inject

class DeleteAllNamesUseCase @Inject constructor(
    private val repository: NameRepository
): BaseUseCase<Unit, Unit>() {
    override suspend fun execute(param: Unit): DataState<Unit> = repository.deleteAll()
}