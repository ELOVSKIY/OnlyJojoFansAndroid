package com.helicoptera.onlyjojofans.ui.registration.recycler

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.helicoptera.onlyjojofans.R
import com.squareup.picasso.Picasso
import java.io.File

class ImageViewHolder(private val view: View) :
    RecyclerView.ViewHolder(view) {

    fun bind(
        imageUrl: String,
        listener: ((String) -> Unit)?
    ) {
        val imageView = view.findViewById<ImageView>(R.id.imageItem)
        imageView.setOnLongClickListener {
            if (listener != null) {
                listener(imageUrl)
            }
            return@setOnLongClickListener true
        }
        Firebase.storage.reference.child(imageUrl).downloadUrl
            .addOnSuccessListener {
            Picasso.get()
                .load(it)
                .fit()
                .centerCrop()
                .into(imageView)
        }
    }

    companion object {
        fun from(parent: ViewGroup): ImageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.image_list_item, parent, false)
            return ImageViewHolder(view)
        }
    }
}