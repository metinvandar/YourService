package com.metinvandar.yourservice.data.model

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("title")
    val title: String,
    @SerializedName("category")
    val category: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("link")
    val link: String
)
