package com.example.avatarapi.util

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.example.avatarapi.R
import com.squareup.picasso.Picasso

fun Activity.mensaje(text: String, length: Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, text, length).show()
}

fun ImageView.loadImage(url: String?, placeholder: Int = R.drawable.loading_anim, error: Int = R.drawable.nouser) {
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(this)
}

fun Context.getStringFormatted(resourceId: Int, value: String?): String {
    return getString(resourceId, value ?: "Unknown")
}