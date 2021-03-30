package com.helicoptera.onlyjojofans.ui.registration.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import java.io.File

class ImageAdapter : ListAdapter<String, ImageViewHolder>(this) {

    private var onClickListener: ((String) -> Unit)? = null

    fun setOnClickListener(listener: ((String) -> Unit)?) {
        onClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
       return ImageViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val imageUrl = getItem(position)
        holder.bind(imageUrl, onClickListener)
    }

    companion object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}