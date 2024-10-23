package com.example.photogallery.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Photo(
    val id: Int,
    val url: String,
    val alt: String,
    val src: ImageDimensions
)