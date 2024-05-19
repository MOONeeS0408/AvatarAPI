package com.example.avatarapi.data.remote

import com.example.avatarapi.data.remote.model.AvatarDetail
import com.example.avatarapi.data.remote.model.AvatarDato
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface AvatarApi {

    @GET
    fun getAvatars(
        @Url url: String
    ): Call<List<AvatarDato>>


    @GET("/api/v1/characters/{id}")
    fun getAvatarDetail(
        @Path("id") id: String,
    ): Call<AvatarDetail>

}