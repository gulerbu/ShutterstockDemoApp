package com.example.demoapp.data

import com.example.demoapp.data.models.GetImagesResponse
import com.example.demoapp.data.network.ResponseHandler
import com.example.demoapp.data.network.Result
import com.example.demoapp.data.source.ImageDataSource

class ImagesRemoteRepository(
    private val dataSource: ImageDataSource,
    private val responseHandler: ResponseHandler
) : ImagesRepository {

    override suspend fun fetchImages(page: Int): Result<GetImagesResponse> {
        return try {
            responseHandler.handleSuccess(dataSource.fetchImages(page))
        } catch (exception: Exception) {
            responseHandler.handleException(exception)
        }
    }
}