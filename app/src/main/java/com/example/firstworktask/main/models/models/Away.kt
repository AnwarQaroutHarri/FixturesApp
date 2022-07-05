package com.example.firstworktask.main.models.models

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class Away(
    val id: Int,
    val logo: String,
    val name: String,
    val winner: Boolean,
    var logoImage: Bitmap
)