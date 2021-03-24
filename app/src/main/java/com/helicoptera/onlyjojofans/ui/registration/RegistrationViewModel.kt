package com.helicoptera.onlyjojofans.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {

    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val stand = MutableLiveData("")
    val height = MutableLiveData("")
    val weight = MutableLiveData("")
    val lat = MutableLiveData("")
    val lon = MutableLiveData("")
    val video = MutableLiveData("")
    val images = MutableLiveData<List<String>>(ArrayList())

    private val  _navigateToList = MutableLiveData(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun onNavigateToList() {
        _navigateToList.value = false
    }

    fun onRegistrationClick() {
        _navigateToList.value = true
    }

    fun onLoadImageClick() {

    }

    fun onLoadVideClick() {

    }
}