package com.metinvandar.yourservice.data.model

import com.google.gson.annotations.SerializedName

data class DetailResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("service_id")
    val serviceId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("long_name")
    val longName: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("average_rating")
    val averageRating: Double,
    @SerializedName("pro_count")
    val proCount: Int,
    @SerializedName("completed_jobs_on_last_month")
    val completedJob: Int
)
