package com.kordia.yourfirstcleanmvi.domain.utils

abstract class BaseUseCase<in P, out R> {
    abstract suspend fun execute(param: P): DataState<R>
}