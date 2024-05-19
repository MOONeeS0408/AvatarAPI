package com.example.videogames.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.videogames.R
import com.example.videogames.data.remote.model.AvatarDto
import com.example.videogames.databinding.AvatarElementBinding

class AvatarAdapter(
    private val avatars: List<AvatarDto>,
    private val onAvatarClicked: (AvatarDto) -> Unit
): RecyclerView.Adapter<AvatarViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val binding = AvatarElementBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        return AvatarViewHolder(binding)
    }

    override fun getItemCount(): Int = avatars.size

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val avatar = avatars[position]
        holder.bind(avatar)

        //usando glide
        Glide.with(holder.itemView.context)
            .load(avatar.image)
            .placeholder(R.drawable.loading_anim)
            .error(R.drawable.nouser)
            .into(holder.ivThumbnail)

        holder.itemView.setOnClickListener{
            onAvatarClicked(avatar)

        }
    }
}