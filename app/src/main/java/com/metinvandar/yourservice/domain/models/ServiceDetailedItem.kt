package com.metinvandar.yourservice.domain.models

data class ServiceDetailedItem(
    val id: Int,
    val name: String,
    val longName: String,
    val averageRating: Double,
    val completedJobs: Int,
    val proCount: Int,
    val imageUrl: String
)
