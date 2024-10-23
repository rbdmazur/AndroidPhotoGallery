package com.example.photogallery.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val API_KEY = "QC51oo8Z7vAxevBvAYSLIFITE42gxLt6fs8BYbd1eMOG8AgCq2rdFsHH"

class PhotoInterceptor : Interceptor {
    private val perPage = 78
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()

        val newUrl: HttpUrl = originalRequest.url.newBuilder()
            .addQueryParameter("per_page", perPage.toString())
            .build()

        val newRequest: Request = originalRequest.newBuilder()
            .url(newUrl)
            .addHeader("Authorization", API_KEY)
            .build()

        return chain.proceed(newRequest)
    }
}