package com.helicoptera.onlyjojofans.data.repository

import com.helicoptera.onlyjojofans.data.model.Character

object CharacterRepository {

    private val characters: MutableSet<Character> = HashSet()

    fun addCharacter(character: Character) {
        characters.add(character)
    }

    fun updateCharacter(character: Character) {
        characters.add(character)
    }

    fun getCharacterById(id: Int): Character? {
        return characters.first { character ->
            character.id == id
        }
    }
}