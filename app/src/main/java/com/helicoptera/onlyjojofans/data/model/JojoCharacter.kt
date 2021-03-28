package com.helicoptera.onlyjojofans.data.model

data class JojoCharacter(
    var authId: String? = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var stand: String =" ",
    var height: Float = 0f,
    var weight: Float = 0f,
    var lat: Float = 0f,
    var lon: Float = 0f,
    var video: String = "",
    var images: List<String> = ArrayList()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as JojoCharacter

        if (authId.isNullOrEmpty()) return false
        if (other.authId.isNullOrEmpty()) return false
        if (authId != other.authId) return false

        return true
    }

    override fun hashCode(): Int {
        return authId?.hashCode() ?: 0
    }
}
