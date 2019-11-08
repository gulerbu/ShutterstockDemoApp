package com.example.demoapp.ui

import com.example.demoapp.data.network.Status
import java.io.Serializable


data class ImageListViewState(
    val status: Status,
    val error: Throwable? = null,
    val data: List<ImageItemUiModel>? = null
) : Serializable {
    fun getImages() = data ?: mutableListOf()

    fun isLoading() = status == Status.LOADING

    fun getErrorMessage() = error?.message

    fun shouldShowError() = error != null

}