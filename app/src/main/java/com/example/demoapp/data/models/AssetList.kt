package com.example.demoapp.data.models

import com.google.gson.annotations.SerializedName

data class AssetList(
    @SerializedName("preview")
    val preview: Asset
)
