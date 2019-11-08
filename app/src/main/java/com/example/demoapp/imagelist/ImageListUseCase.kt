package com.example.demoapp.imagelist

import com.example.demoapp.data.ImagesRepository
import com.example.demoapp.data.network.Result
import com.example.demoapp.ui.ImageItemUiModel

class ImageListUseCase(private val repository: ImagesRepository) {

    suspend fun fetchImages(page: Int): Result<List<ImageItemUiModel>> {

        val result = repository.fetchImages(page)
        val data = result.data?.let { getImagesResponse ->
            getImagesResponse.images.map { ImageItemUiModel(it.description, it.assets.preview.url) }
        }

        return Result(result.status, data, result.error)

    }
}