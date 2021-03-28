package com.helicoptera.onlyjojofans.data.utils

import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository

private const val TAG = "AuthUtils"

object AuthUtils {

    private val auth = Firebase.auth
    private val firestore = Firebase.firestore

    fun createUser(character: JojoCharacter, listener: (character: JojoCharacter?) -> Unit) {
        auth.createUserWithEmailAndPassword(character.email, character.password)
            .addOnCompleteListener() { authResult ->
                if (authResult.isSuccessful) {
                    character.authId = Firebase.auth.currentUser?.tenantId ?: ""
                    CharacterRepository.addCharacter(character)
                    listener(character)
                } else {
                    listener(null)
                }
            }.addOnFailureListener {
                Log.d(TAG, "Registrations failed with ${it.message}")
            }
    }

    fun updateCharacter(character: JojoCharacter, listener: (character: JojoCharacter?) -> Unit) {

        auth.createUserWithEmailAndPassword(character.email, character.password)
            .addOnCompleteListener() { authResult ->
                if (authResult.isSuccessful) {
                    character.authId = Firebase.auth.currentUser?.tenantId ?: ""
                    CharacterRepository.addCharacter(character)
                    listener(character)
                } else {
                    listener(null)
                }
            }.addOnFailureListener {
                Log.d(TAG, "Registrations failed with ${it.message}")
            }
    }

    fun authorization(
        email: String,
        password: String,
        listener: (character: JojoCharacter?) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val id = user.tenantId
                    val character = CharacterRepository.getCharacterById(id)
                    listener(JojoCharacter())
                } else {
                    listener(null)
                }
            }
            .addOnFailureListener {
                Log.d(TAG, "Authorization failed with ${it.message}")
            }
    }

    fun getCurrentUser(): JojoCharacter? {
        val userId = Firebase.auth.currentUser?.tenantId
        val character = CharacterRepository.getCharacterById(userId)

        return character
    }
}