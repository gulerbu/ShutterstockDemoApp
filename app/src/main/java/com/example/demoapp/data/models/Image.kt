package com.example.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("id")
    val id: String,
    @SerializedName("aspect")
    val aspect: Double,
    @SerializedName("assets")
    val assets: AssetList,
    @SerializedName("description")
    val description: String
)