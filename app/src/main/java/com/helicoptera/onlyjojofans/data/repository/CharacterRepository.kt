package com.helicoptera.onlyjojofans.data.repository

import com.helicoptera.onlyjojofans.data.model.JojoCharacter

object CharacterRepository {

    private val JOJO_CHARACTERS: MutableSet<JojoCharacter> = HashSet()

    fun addCharacter(jojoCharacter: JojoCharacter) {
        JOJO_CHARACTERS.add(jojoCharacter)
    }

    fun updateCharacter(jojoCharacter: JojoCharacter) {
        JOJO_CHARACTERS.add(jojoCharacter)
    }

    fun getCharacterById(id: String?): JojoCharacter? {
        return JOJO_CHARACTERS.first { character ->
            character.id == id
        }
    }
}