package com.example.photogallery.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDimensions(
    val medium: String,
    val large: String,
    val large2x: String,
    val original: String
)