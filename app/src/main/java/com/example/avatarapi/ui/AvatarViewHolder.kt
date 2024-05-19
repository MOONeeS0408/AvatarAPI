package com.example.avatarapi.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.avatarapi.R
import com.example.avatarapi.data.remote.model.AvatarDato
import com.example.avatarapi.databinding.AvatarElementBinding

class AvatarViewHolder (
    private val  binding: AvatarElementBinding,

): RecyclerView.ViewHolder(binding.root) {

    val ivImage = binding.ivImage

    fun bind(avatar: AvatarDato){

        try {
            binding.tvName.text = avatar.name
            binding.tvAfiliacion.text = avatar.affil?: "Unknown"
        }catch (e: Exception){
            Log.e("AvatarViewHolder", itemView.context.getString(R.string.error_msg), e)
            binding.tvName.text = itemView.context.getString(R.string.nAvailable)
            binding.tvAfiliacion.text = itemView.context.getString(R.string.affiliation, itemView.context.getString(R.string.unknown))
            ivImage.setImageResource(R.drawable.nouser)
        }
    }
}