package com.helicoptera.onlyjojofans.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.helicoptera.onlyjojofans.data.model.JojoCharacter

object CharacterRepository {

    init {
        val firestore = Firebase.firestore
        firestore.collection("characters")
    }

    private val _characters: MutableLiveData<MutableSet<JojoCharacter>> = MutableLiveData(HashSet())
    val characters: LiveData<Set<JojoCharacter>>
        get() = _characters as LiveData<Set<JojoCharacter>>

    fun addCharacter(jojoCharacter: JojoCharacter) {
        _characters.value?.add(jojoCharacter)
    }

    fun updateCharacter(jojoCharacter: JojoCharacter) {
        _characters.value?.add(jojoCharacter)
    }

    fun getCharacterById(id: String?): JojoCharacter? {
        return _characters.value?.first { character ->
            character.id == id
        }
    }
}