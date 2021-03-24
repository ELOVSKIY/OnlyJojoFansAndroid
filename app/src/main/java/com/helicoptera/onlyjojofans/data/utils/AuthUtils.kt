package com.helicoptera.onlyjojofans.data.utils

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository
import com.helicoptera.onlyjojofans.data.storage.StorageUtils

private const val TAG = "AuthUtils"

object AuthUtils {

    fun createUser(character: JojoCharacter, listener: (character: JojoCharacter?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(character.email, character.password)
            .addOnCompleteListener() { authResult ->
                if (authResult.isSuccessful) {
                    character.id = Firebase.auth.currentUser?.tenantId ?: ""
                    CharacterRepository.addCharacter(character)
                    listener(character)
                } else {
                    listener(null)
                }
            }.addOnFailureListener {
                Log.d(TAG, "Authorization failed")
            }
    }
    
    fun authorization(email: String, password: String) {
    }

    fun getCurrentUser() : JojoCharacter? {
        val userId = Firebase.auth.currentUser?.tenantId
        val character = CharacterRepository.getCharacterById(userId)

        return character
    }
}