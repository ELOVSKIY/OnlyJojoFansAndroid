package com.helicoptera.onlyjojofans.data.model

data class JojoCharacter(
    var id: String? = "",
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var stand: String =" ",
    var height: Float = 0f,
    var weight: Float = 0f,
    var lat: Float = 0f,
    var lon: Float = 0f,
    var video: String = "",
    var images: List<String> = ArrayList()
)
