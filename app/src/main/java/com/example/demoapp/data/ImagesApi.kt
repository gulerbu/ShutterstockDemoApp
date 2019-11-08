package com.example.demoapp.data

import com.example.demoapp.data.models.GetImagesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImagesApi {

    @Headers(
        "Authorization: Basic MjUxMWUtMThjMzctODk4M2QtNDRhYjktZTUwNTItMzBhMTU6NTk3ODgtODE4NjctOTJkZDctYTIwYTctMzM0N2ItYjdjZDk=",
        "accept: application/json",
        "user-agent: DemoApp"
    )
    @GET("images/search")
    suspend fun fetchImages(@Query("page") page: Int): GetImagesResponse
}