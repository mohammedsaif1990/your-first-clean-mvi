package com.kordia.yourfirstcleanmvi.presentation.utils.appstructure

import androidx.lifecycle.ViewModel

/**
 * BaseViewModel class the parent of all view models.
 *
 * @author Mohammedsaif Kordia
 */
@Suppress("PropertyName", "UNCHECKED_CAST")
abstract class BaseViewModel<T: BaseIntent> : ViewModel() {

    private var _intent: BaseIntent? = null

    protected val event: T
        get() = _intent as T

    abstract fun onTriggerEvent(eventType: T)
}