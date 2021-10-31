package com.metinvandar.yourservice.view.home

import com.metinvandar.yourservice.domain.models.HomeData
import com.metinvandar.yourservice.utils.UIState

data class HomeUIState(
    override val loading: Boolean = false,
    override val isConnected: Boolean = true,
    override val error: Boolean = false,
    val homeData: HomeData = HomeData(
        services = emptyList(),
        popularServices = emptyList(),
        posts = emptyList()
    )
): UIState