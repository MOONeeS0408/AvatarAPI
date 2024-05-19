package com.example.avatarapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class AvatarDetail(
    @SerializedName("name")
    var name: String,

    @SerializedName("allies")
    var allies: List<String>,

    @SerializedName("enemies")
    var enemies: List<String>,

    @SerializedName("photoUrl")
    var image: String,

    @SerializedName("position")
    var position: String,

    @SerializedName("profession")
    var profession: String,

    @SerializedName("gender")
    var gender: String

)
