package com.example.videogames.ui

import androidx.recyclerview.widget.RecyclerView
import com.example.videogames.data.remote.model.AvatarDto
import com.example.videogames.databinding.AvatarElementBinding
import com.example.videogames.R

class AvatarViewHolder (
    private val  binding: AvatarElementBinding,

): RecyclerView.ViewHolder(binding.root) {

    val ivThumbnail = binding.ivThumbnail

    fun bind(avatar: AvatarDto){
        binding.tvName.text = avatar.name
        binding.tvAfiliacion.text = avatar.affiliation?: "Unknown"

        //binding.tvAliados.text = avatar.allies.toString()
    }
}