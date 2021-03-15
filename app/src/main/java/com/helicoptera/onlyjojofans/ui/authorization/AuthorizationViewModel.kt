package com.helicoptera.onlyjojofans.ui.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthorizationViewModel : ViewModel() {

    private val  _onNavigationToList = MutableLiveData(false)
    val onNavigationToList: LiveData<Boolean>
        get() = _onNavigationToList

    fun onAuthorizationClick() {
        _onNavigationToList.value = true
    }

    fun onNavigateToList() {
        _onNavigationToList.value = false
    }

    private val  _onNavigationToRegistration = MutableLiveData(false)
    val onnNavigationToRegistration: LiveData<Boolean>
        get() = _onNavigationToRegistration

    fun onRegistrationClick() {
        _onNavigationToRegistration.value = true
    }

    fun onNavigateToRegistration() {
        _onNavigationToRegistration.value = false
    }
}