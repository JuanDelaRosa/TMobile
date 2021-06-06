package com.juandelarosa.tmobile.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InfoActivityViewModel  : ViewModel() {
    fun prepateUI(url: String, title: String) {
        _url.postValue(url)
        _title.postValue(title)
        _description.postValue("This information could be available if we consume another service")
    }

    private val _url = MutableLiveData<String>()
    val url = _url

    private val _title = MutableLiveData<String>()
    val title = _title

    private val _description = MutableLiveData<String>()
    val description = _description
}