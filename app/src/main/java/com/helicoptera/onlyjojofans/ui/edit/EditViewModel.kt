package com.helicoptera.onlyjojofans.ui.edit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.helicoptera.onlyjojofans.data.model.JojoCharacter
import com.helicoptera.onlyjojofans.data.repository.CharacterRepository
import com.helicoptera.onlyjojofans.data.storage.StorageUtils

class EditViewModel : ViewModel() {

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

    private val _chooseImage = MutableLiveData(false)
    val chooseImage: LiveData<Boolean>
        get() = _chooseImage

    private val _chooseVideo = MutableLiveData(false)
    val chooseVideo: LiveData<Boolean>
        get() = _chooseImage

    private val _navigateToDetail = MutableLiveData(false)
    val navigateToDetail: LiveData<Boolean>
        get() = _navigateToDetail

    fun initializeViewModel(jojoCharacter: JojoCharacter) {
        name.value = jojoCharacter.name
        email.value = jojoCharacter.email
        password.value = jojoCharacter.password
        stand.value = jojoCharacter.stand
        height.value = jojoCharacter.height.toString()
        weight.value = jojoCharacter.weight.toString()
        lat.value = jojoCharacter.lat.toString()
        lon.value = jojoCharacter.lon.toString()
        _video.value = jojoCharacter.video
        _images.value = jojoCharacter.images.toMutableList()
    }

    fun onNavigateTODetail() {
        _navigateToDetail.value = false
    }

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

    fun onConfirmClick() {
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
        character.authId = CharacterRepository.currentCharacter!!.authId
        CharacterRepository.updateCharacter(character) { character ->
            if (character != null) {
                _navigateToDetail.value = true
            }
        }
    }
}