package com.metinvandar.yourservice.data.model

import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("id")
    val id: Int,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("long_name")
    val name: String,
    @SerializedName("name")
    val type: String,
    @SerializedName("image_url")
    val imageUrl: String
)
