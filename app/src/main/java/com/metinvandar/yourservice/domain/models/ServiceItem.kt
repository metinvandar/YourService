package com.metinvandar.yourservice.domain.models

data class ServiceItem(
    val id: Int,
    val name: String,
    val type: ServiceItemType,
    val imageUrl: String?
)
