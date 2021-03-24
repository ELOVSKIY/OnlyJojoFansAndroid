package com.helicoptera.onlyjojofans.data.storage

import android.net.Uri
import android.util.Log
import com.google.android.gms.auth.api.signin.internal.Storage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageMetadata
import com.google.firebase.storage.ktx.storage
import com.google.firebase.storage.ktx.storageMetadata
import java.io.File
import java.io.FileInputStream
import java.util.*

private const val TAG = "StorageUtils"

object StorageUtils {

    private val storage = Firebase.storage

    fun uploadMedia(imagePath : String, block: (String) -> Unit) {
        val metadata = StorageMetadata.Builder()
            .setContentType("image/jpeg")
            .build()

        val file = File(imagePath)
        val data = file.readBytes()

        Log.d(TAG, "All bytes: ${data.size}")
        var uploadTask = storage.reference.child(file.name).putBytes(data, metadata)
        uploadTask.addOnFailureListener {
           Log.d(TAG,"FAILURE")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d(TAG,"SUCCESS")
            block(file.name)
        }.addOnProgressListener {
            Log.d(TAG, it.bytesTransferred.toString())
        }.addOnCompleteListener {
            Log.d(TAG,"ON COMPLETE")
        }



    }
}