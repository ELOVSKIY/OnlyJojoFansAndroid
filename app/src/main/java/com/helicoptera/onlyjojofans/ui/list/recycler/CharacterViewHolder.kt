package com.helicoptera.onlyjojofans.ui.list.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.databinding.CharacterListItemBinding

class CharacterViewHolder(private val binding: CharacterListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(character: JojoCharacter) {
        binding.character = character
    }

    companion object {
        fun from(parent: ViewGroup): CharacterViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = CharacterListItemBinding.inflate(inflater, parent, false)
            return CharacterViewHolder(binding)
        }
    }
}