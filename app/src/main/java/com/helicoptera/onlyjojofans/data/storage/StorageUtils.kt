package com.helicoptera.onlyjojofans.data.storage

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.ktx.storage
import java.io.File
import java.util.*

private const val TAG = "StorageUtils"

object StorageUtils {

    private val storage = Firebase.storage

    fun uploadMedia(imagePath : String, listener: (String) -> Unit) {
        val metadata = StorageMetadata.Builder()
            .setContentType("image/jpeg")
            .build()

        val file = File(imagePath)
        val data = file.readBytes()

        Log.d(TAG, "All bytes: ${data.size}")
        val fileName = "${UUID.randomUUID()}.${file.extension}"
        var uploadTask = storage.reference.child(fileName).putBytes(data, metadata)
        uploadTask.addOnFailureListener {
            Log.d(TAG,"FAILURE")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d(TAG,"SUCCESS")
            listener(fileName)
        }.addOnProgressListener {
            Log.d(TAG, it.bytesTransferred.toString())
        }.addOnCompleteListener {
            Log.d(TAG,"ON COMPLETE")
        }

    }
}