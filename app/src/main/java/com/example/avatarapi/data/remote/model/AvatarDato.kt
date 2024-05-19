package com.example.avatarapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class AvatarDato(
    @SerializedName("_id")
    var id: String,

    @SerializedName("name")
    var name: String,

    @SerializedName("photoUrl")
    var img: String,

    @SerializedName("affiliation")
    var affil: String,

)


