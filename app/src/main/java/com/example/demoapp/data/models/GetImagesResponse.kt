package com.example.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class GetImagesResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("data")
    val images: ArrayList<Image>
)