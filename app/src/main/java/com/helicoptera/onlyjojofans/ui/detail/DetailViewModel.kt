package com.helicoptera.onlyjojofans.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailViewModel : ViewModel() {

    private val _navigateToEdit = MutableLiveData(false)
    val navigateToEdit: LiveData<Boolean>
    get() = _navigateToEdit

    fun onNavigateToEdit() {
        _navigateToEdit.value = false
    }

    fun onEditClick() {
        _navigateToEdit.value = true
    }
}