package com.example.demoapp.data

import com.example.demoapp.data.models.GetImagesResponse
import com.example.demoapp.data.network.Result

interface ImagesRepository {

    suspend fun fetchImages(page: Int): Result<GetImagesResponse>
}