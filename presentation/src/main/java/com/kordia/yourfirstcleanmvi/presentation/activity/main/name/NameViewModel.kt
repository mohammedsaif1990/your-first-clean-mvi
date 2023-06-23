package com.kordia.yourfirstcleanmvi.presentation.activity.main.name

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kordia.yourfirstcleanmvi.domain.entity.NameEntity
import com.kordia.yourfirstcleanmvi.domain.repository.NameRepository
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
    private val nameRepository: NameRepository
) : BaseViewModel<NameIntent>() {

    private var _namesLiveData = MutableLiveData<DataState<List<NameEntity>>>()
    val namesLiveData: LiveData<DataState<List<NameEntity>>>
        get() = _namesLiveData

    private suspend fun insertName(model: NameIntent.InsertName) {
        nameRepository.insertName(NameEntity(0L, model.name)).collect()
        getAll()
    }

    private suspend fun deleteAll() {
        nameRepository.deleteAll()
        _namesLiveData.postValue(DataState.Empty)
    }

    private suspend fun getAll() {
        nameRepository.getAll().collect { dataState ->
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