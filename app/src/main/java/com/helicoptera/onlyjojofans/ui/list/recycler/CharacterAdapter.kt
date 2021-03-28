package com.helicoptera.onlyjojofans.ui.list.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.helicoptera.onlyjojofans.data.model.JojoCharacter

class CharacterAdapter : ListAdapter<JojoCharacter, CharacterViewHolder>(this) {

    private var onClickListener: ((JojoCharacter) -> Unit)? = null

    fun setOnClickListener(listener: ((JojoCharacter) -> Unit)?) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
       return CharacterViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        holder.bind(character, onClickListener)
    }

    companion object : DiffUtil.ItemCallback<JojoCharacter>() {
        override fun areItemsTheSame(oldItem: JojoCharacter, newItem: JojoCharacter): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: JojoCharacter, newItem: JojoCharacter): Boolean {
            return oldItem == newItem
        }
    }
}