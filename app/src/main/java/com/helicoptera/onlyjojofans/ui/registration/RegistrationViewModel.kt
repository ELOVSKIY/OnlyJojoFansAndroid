package com.helicoptera.onlyjojofans.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.data.storage.StorageUtils
import com.helicoptera.onlyjojofans.data.utils.AuthUtils

class RegistrationViewModel : ViewModel() {

    val name = MutableLiveData("")
    val email = MutableLiveData("")
    val password = MutableLiveData("")
    val stand = MutableLiveData("")
    val height = MutableLiveData("")
    val weight = MutableLiveData("")
    val lat = MutableLiveData("")
    val lon = MutableLiveData("")

    private val _video = MutableLiveData("")
    val video: LiveData<String>
        get() = _video

    private val _images = MutableLiveData<MutableList<String>>(ArrayList())
    val images: LiveData<MutableList<String>>
        get() = _images

    private val _navigateToList = MutableLiveData(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    private val _chooseImage = MutableLiveData(false)
    val chooseImage: LiveData<Boolean>
        get() = _chooseImage

    private val _chooseVideo = MutableLiveData(false)
    val chooseVideo: LiveData<Boolean>
        get() = _chooseImage

    fun onImagesReceive(mediaPaths: ArrayList<String>) {
        if (mediaPaths.isNotEmpty()) {
            StorageUtils.uploadMedia(mediaPaths[0]) { url ->
                val imagesPath = _images.value!!
                imagesPath.addAll(mediaPaths)
                _images.value = imagesPath
            }
        }
    }

    fun onVideoReceive(mediaPaths: ArrayList<String>) {
        if (mediaPaths.isNotEmpty()) {
            StorageUtils.uploadMedia(mediaPaths[0]) { url ->
                _video.value = url
            }
        }
    }

    fun removeImage(name: String) {
        val images = _images.value!!
        images.remove(name)
        _images.value = images
    }

    fun removeVideo() {
        _video.value = ""
    }

    fun onLoadImageClick() {
        _chooseImage.value = true
    }

    fun onLoadImage() {
        _chooseImage.value = false
    }

    fun onLoadVideClick() {
        _chooseVideo.value = true
    }

    fun onLoadVideo() {
        _chooseVideo.value = false
    }


    fun onRegistrationClick() {
        val character = JojoCharacter().also {
            it.name = name.value ?: ""
            it.email = email.value ?: ""
            it.password = password.value ?: ""
            it.stand = stand.value ?: ""
            it.height = (height.value ?: "").toFloatOrNull() ?: 0f
            it.weight = (weight.value ?: "").toFloatOrNull() ?: 0f
            it.lat = (lat.value ?: "").toFloatOrNull() ?: 0f
            it.lon = (lon.value ?: "").toFloatOrNull() ?: 0f
            it.images = _images.value ?: ArrayList()
            it.video = _video.value ?: ""
        }
        AuthUtils.createUser(character) { character ->
            if (character != null) {
                _navigateToList.value = true
            }
        }
    }


    fun onNavigateToList() {
        _navigateToList.value = false
    }
}