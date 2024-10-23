package com.example.photogallery

import com.example.photogallery.api.PexelsApi
import com.example.photogallery.api.PhotoInterceptor
import com.example.photogallery.models.Photo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create


class PhotoRepository {
    private val pexelsApi: PexelsApi

    init {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(PhotoInterceptor())
            .build()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.pexels.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()

        pexelsApi = retrofit.create<PexelsApi>()
    }

    suspend fun fetchContents(): List<Photo> =
        pexelsApi.fetchPhotos().photosList

    suspend fun searchPhoto(searchQuery: String): List<Photo> =
        pexelsApi.searchPhoto(searchQuery).photosList
}