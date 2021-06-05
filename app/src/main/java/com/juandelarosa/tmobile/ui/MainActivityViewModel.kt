package com.juandelarosa.tmobile.ui

import androidx.lifecycle.*
import com.juandelarosa.domain.entities.Cards
import com.juandelarosa.tmobile.app.TMobileApp
import kotlinx.coroutines.launch
import com.juandelarosa.domain.common.Result

class MainActivityViewModel(private val app: TMobileApp) : ViewModel() {

    private val _dataLoading = MutableLiveData(true)
    val dataLoading: LiveData<Boolean> = _dataLoading

    private val _homeFeeds = MutableLiveData<List<Cards>>()
    val homeFeeds = _homeFeeds

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getHomeFeeds(){
        viewModelScope.launch {
            _dataLoading.postValue(true)
            when(val result = app.getHomeFeeds.invoke()){
                is Result.Success ->{
                    _homeFeeds.postValue(result.data)
                    _dataLoading.postValue(false)
                }
                is Result.Error ->{
                    _error.postValue(result.exception.message)
                }
            }
        }
    }

    class MainActivityViewModelFactory(private val app: TMobileApp) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(app) as T
        }
    }
}