package com.example.videogames.data.remote

import com.example.videogames.data.remote.model.AvatarDetailDto
import com.example.videogames.data.remote.model.AvatarDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface AvatarApi {

    @GET
    fun getAvatars(
        @Url url: String
    ): Call<List<AvatarDto>>


    @GET("/api/v1/characters/{id}")
    fun getAvatarDetail(
        @Path("id") id: String,
    ): Call<AvatarDetailDto>

}