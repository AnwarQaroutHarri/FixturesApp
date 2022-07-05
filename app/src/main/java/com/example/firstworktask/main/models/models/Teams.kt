package com.example.firstworktask.main.models.models

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

data class Teams(
    val away: Away,
    val home: Home
) {
    companion object {
        @JvmStatic
        @BindingAdapter("teamImage")
        fun loadImage(view: ImageView, homeTeamImage: String) {
            Glide.with(view.context)
                .load(homeTeamImage)
                .into(view)
        }
    }
}