package com.helicoptera.onlyjojofans.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository

class ListViewModel : ViewModel() {

    val characters: LiveData<Set<JojoCharacter>>
        get() = CharacterRepository.characters

    private val _currentCharacter = MutableLiveData<JojoCharacter?>(null)
    val currentCharacter: LiveData<JojoCharacter?>
        get() = _currentCharacter

    fun setCurrentCharacter(character: JojoCharacter) {
        _currentCharacter.value = character
    }

    fun onNavigateToDetail() {
        _currentCharacter.value = null
    }
}