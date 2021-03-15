package com.helicoptera.onlyjojofans.data.model

data class Character(
    val id: Int,
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
        if (other !is Character) {
            return false
        }

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return this.id
    }
}
