package com.helicoptera.onlyjojofans.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.helicoptera.onlyjojofans.data.model.JojoCharacter

private const val COLLECTION_NAME = "characters"

object CharacterRepository {

    var currentCharacter: JojoCharacter? = null

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
                document?.let { document ->
                    val character = document.toObject(JojoCharacter::class.java)
                    if (character != null) {
                        characters.add(character)
                        character.authId = document.id
                    }
                }
            }

            _characters.postValue(characters)
        }
    }

    private val _characters: MutableLiveData<MutableSet<JojoCharacter>> = MutableLiveData(HashSet())
    val characters: LiveData<Set<JojoCharacter>>
        get() {
            return _characters as LiveData<Set<JojoCharacter>>
        }

    fun addCharacter(jojoCharacter: JojoCharacter) {
        firestore.collection(COLLECTION_NAME).add(jojoCharacter).addOnCompleteListener {
            jojoCharacter.authId = it.result?.id
            val values = _characters.value
            values?.add(jojoCharacter)
            _characters.value = values
        }
    }

    fun updateCharacter(jojoCharacter: JojoCharacter, listener: (JojoCharacter?) -> Unit) {
        firestore.collection(COLLECTION_NAME).document(jojoCharacter.authId!!).set(jojoCharacter)
            .addOnSuccessListener {
                _characters.value?.add(jojoCharacter)
                listener(jojoCharacter)
            }.addOnFailureListener {
                listener(null)
            }
    }
}