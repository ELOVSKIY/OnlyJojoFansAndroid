package com.helicoptera.onlyjojofans.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository

class ListViewModel : ViewModel() {

    val characters: LiveData<Set<JojoCharacter>>
        get() = CharacterRepository.characters
}