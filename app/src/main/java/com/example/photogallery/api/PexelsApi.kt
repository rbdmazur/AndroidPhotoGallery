package com.example.photogallery.api

import com.example.photogallery.models.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PexelsApi {

    @GET("curated/")
    suspend fun fetchPhotos(): PhotoResponse

    @GET("search/")
    suspend fun searchPhoto(@Query("query") query: String): PhotoResponse
}