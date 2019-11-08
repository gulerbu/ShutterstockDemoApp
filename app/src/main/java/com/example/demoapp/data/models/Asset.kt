package com.example.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class Asset(
    @SerializedName("height")
    val height: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("width")
    val width: Int)