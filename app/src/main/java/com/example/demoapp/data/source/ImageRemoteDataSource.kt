package com.example.demoapp.data.source

import com.example.demoapp.data.ImagesApi
import com.example.demoapp.data.models.GetImagesResponse

class ImageRemoteDataSource(private val imagesApi: ImagesApi) : ImageDataSource {

    override suspend fun fetchImages(page: Int): GetImagesResponse = imagesApi.fetchImages(page = page)
}