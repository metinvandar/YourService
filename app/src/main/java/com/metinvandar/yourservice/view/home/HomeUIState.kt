package com.metinvandar.yourservice.view.home

import com.metinvandar.yourservice.domain.models.HomeData

data class HomeUIState(
    val isLoading: Boolean = false,
    val homeData: HomeData = HomeData(
        services = emptyList(),
        popularServices = emptyList(),
        posts = emptyList()
    ),
    val errorMessage: String? = null,
)