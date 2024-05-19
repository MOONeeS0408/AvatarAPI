package com.example.avatarapi.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.avatarapi.R
import com.example.avatarapi.data.remote.model.AvatarDato
import com.example.avatarapi.databinding.AvatarElementBinding
import com.example.avatarapi.util.loadImage

class AvatarAdapter(
    private val avatars: List<AvatarDato>,
    private val onAvatarClicked: (AvatarDato) -> Unit
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
        try {
            val avatar = avatars[position]
            holder.bind(avatar)
            holder.ivImage.loadImage(avatar.img) //Picasso
            holder.itemView.setOnClickListener{
                onAvatarClicked(avatar)

            }
        }catch (e: Exception){
            Log.e("AvatarAdapter", holder.itemView.context.getString(R.string.error_avatar), e)
        }
    }
}