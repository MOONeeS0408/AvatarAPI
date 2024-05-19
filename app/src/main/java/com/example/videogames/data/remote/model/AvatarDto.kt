package com.example.videogames.data.remote.model

import com.google.gson.annotations.SerializedName

data class AvatarDto(
    @SerializedName("_id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("photoUrl")
    var image: String,

    @SerializedName("affiliation")
    var affiliation: String,

    @SerializedName("position")
    var position: String,

    @SerializedName("profession")
    var profession: String,

    @SerializedName("gender")
    var gender: String

)


