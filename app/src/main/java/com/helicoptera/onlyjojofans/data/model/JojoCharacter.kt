package com.helicoptera.onlyjojofans.data.model

data class JojoCharacter(
    var id: String,
    val name: String,
    val email: String,
    val password: String,
    val stand: String,
    val height: Float,
    val weight: Float,
    val lan: Float,
    val lon: Float

) {
    override fun equals(other: Any?): Boolean {
        if (other !is JojoCharacter) {
            return false
        }

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id.hashCode()
    }
}
