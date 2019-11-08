package com.example.demoapp.data.source

import com.example.demoapp.data.models.GetImagesResponse

interface ImageDataSource {

    suspend fun fetchImages(page: Int): GetImagesResponse
}