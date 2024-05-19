package com.example.videogames.data.remote.model

import com.google.gson.annotations.SerializedName

data class AvatarDetailDto(
    @SerializedName("name")
    var name: String,

    @SerializedName("photoUrl")
    var image: String,

    @SerializedName("allies")
    var allies: List<String>,

    @SerializedName("enemies")
    var enemies: List<String>, //ArrayList<String> = arrayListOf(),

    @SerializedName("affiliation")
    var affiliation: String,

    @SerializedName("position")
    var position: String,

    @SerializedName("profession")
    var profession: String,

    @SerializedName("gender")
    var gender: String

)
