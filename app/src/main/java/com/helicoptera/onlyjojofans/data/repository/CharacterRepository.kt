package com.helicoptera.onlyjojofans.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.helicoptera.onlyjojofans.data.model.JojoCharacter

private const val COLLECTION_NAME = "characters"

object CharacterRepository {

    private val firestore = Firebase.firestore

    init {
        collect()
    }

    private fun collect() {
        firestore.collection(COLLECTION_NAME).addSnapshotListener { snapshot, e ->
            if (e != null || snapshot == null) {
                return@addSnapshotListener
            }

            val characters = HashSet<JojoCharacter>()
            for (document in snapshot.documents) {
                val character = document?.toObject(JojoCharacter::class.java)
                if (character != null) {
                    characters.add(character)
                }
            }

            _characters.postValue(characters)
        }
    }

    private val _characters: MutableLiveData<MutableSet<JojoCharacter>> = MutableLiveData(HashSet())
    val characters: LiveData<Set<JojoCharacter>>
        get() {
            collect()
            return _characters as LiveData<Set<JojoCharacter>>
        }

    fun addCharacter(jojoCharacter: JojoCharacter) {
        val ref = firestore.collection(COLLECTION_NAME).add(jojoCharacter).addOnCompleteListener {
            _characters.value?.add(jojoCharacter)
            jojoCharacter.id = it.result?.id
        }
    }

    fun updateCharacter(jojoCharacter: JojoCharacter) {
        _characters.value?.add(jojoCharacter)
    }

    fun getCharacterById(id: String?): JojoCharacter? {
        return null
        return _characters.value?.first { character ->
            character.id == id
        }
    }
}