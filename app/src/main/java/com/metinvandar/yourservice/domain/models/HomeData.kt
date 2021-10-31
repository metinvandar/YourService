package com.metinvandar.yourservice.domain.models

data class HomeData(
    val services: List<ServiceItem>,
    val popularServices: List<ServiceItem>,
    val posts: List<PostItem>
)
