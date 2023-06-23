package com.kordia.yourfirstcleanmvi.domain.utils

import kotlinx.coroutines.flow.Flow

abstract class FlowableUseCase<in P, out R> {
    abstract suspend fun execute(param: P): Flow<DataState<R>>
}