package com.helicoptera.onlyjojofans.data.model

data class JojoCharacter(
    var id: String,
    val name: String,
    val email: String,
    val password: String,
    val stand: String,
    val height: Float,
    val weight: Float,
    val lat: Float,
    val lon: Float,
    val video: String,
    val images: List<String>
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
