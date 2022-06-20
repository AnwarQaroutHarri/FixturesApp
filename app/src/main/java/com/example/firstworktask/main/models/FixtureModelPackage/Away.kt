package com.example.firstworktask.main.models.FixtureModelPackage

import android.graphics.Bitmap

data class Away(
    val id: Int,
    val logo: String,
    val name: String,
    val winner: Boolean,
    var logoImage: Bitmap
)