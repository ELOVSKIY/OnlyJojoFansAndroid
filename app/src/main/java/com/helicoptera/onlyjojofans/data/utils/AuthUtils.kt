package com.helicoptera.onlyjojofans.data.utils

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AuthUtils {

    fun createUser(email: String, password: String, listener: (character: Character?) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener() { authResult ->
                if (authResult.isSuccessful) {
                    Firebase.auth.currentUser.tenantId
                } else {
                    listener(null)
                }
            }
    }
}