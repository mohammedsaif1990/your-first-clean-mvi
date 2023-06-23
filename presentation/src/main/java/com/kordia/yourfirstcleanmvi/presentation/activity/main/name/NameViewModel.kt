package com.kordia.yourfirstcleanmvi.presentation.activity.main.name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kordia.yourfirstcleanmvi.domain.entity.NameEntity
import com.kordia.yourfirstcleanmvi.domain.usecase.DeleteAllNamesUseCase
import com.kordia.yourfirstcleanmvi.domain.usecase.GetAllNamesUseCase
import com.kordia.yourfirstcleanmvi.domain.usecase.InsertNameUseCase
import com.kordia.yourfirstcleanmvi.domain.utils.DataState
import com.kordia.yourfirstcleanmvi.presentation.utils.appstructure.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * NameViewModel class.
 *
 * @author Mohammedsaif Kordia
 */
@HiltViewModel
class NameViewModel @Inject constructor(
    private val insertNameUseCase: InsertNameUseCase,
    private val getAllNamesUseCase: GetAllNamesUseCase,
    private val deleteAllNamesUseCase: DeleteAllNamesUseCase,
) : BaseViewModel<NameIntent>() {

    private var _namesLiveData = MutableLiveData<DataState<List<NameEntity>>>()
    val namesLiveData: LiveData<DataState<List<NameEntity>>>
        get() = _namesLiveData

    private suspend fun insertName(model: NameIntent.InsertName) {
        insertNameUseCase.execute(NameEntity(0L, model.name)).collect()
        getAll()
    }

    private suspend fun deleteAll() {
        deleteAllNamesUseCase.execute(Unit)
        _namesLiveData.postValue(DataState.Empty)
    }

    private suspend fun getAll() {
        getAllNamesUseCase.execute(Unit).collect { dataState ->
            _namesLiveData.postValue(dataState)
        }
    }

    override fun onTriggerEvent(eventType: NameIntent) {
        viewModelScope.launch {
            when (eventType) {
                is NameIntent.DeleteAll -> deleteAll()
                is NameIntent.InsertName -> insertName(eventType)
                is NameIntent.GetAll -> getAll()
            }
        }
    }
}