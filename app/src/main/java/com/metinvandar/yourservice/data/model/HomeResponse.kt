package com.metinvandar.yourservice.data.model

import com.google.gson.annotations.SerializedName

data class HomeResponse(
    @SerializedName("all_services")
    val allServices: List<Service>,
    @SerializedName("popular")
    val popular: List<Service>,
    @SerializedName("posts")
    val posts: List<Post>
)
