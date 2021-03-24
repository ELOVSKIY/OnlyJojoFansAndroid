package com.helicoptera.onlyjojofans.ui.authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthorizationViewModel : ViewModel() {

    private val  _navigateToList = MutableLiveData(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun onAuthorizationClick() {
        _navigateToList.value = true
    }

    fun onNavigateToList() {
        _navigateToList.value = false
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