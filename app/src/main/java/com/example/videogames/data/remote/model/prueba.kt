package com.example.videogames.data.remote.model

class prueba : ArrayList<pruebaItem>()

data class pruebaItem(
    val _id: String,
    val affiliation: String,
    val allies: List<String>,
    val enemies: List<String>,
    val eye: String,
    val first: String,
    val gender: String,
    val hair: String,
    val love: String,
    val name: String,
    val photoUrl: String,
    val position: String,
    val predecessor: String,
    val profession: String,
    val skin: String,
    val weapon: String
)