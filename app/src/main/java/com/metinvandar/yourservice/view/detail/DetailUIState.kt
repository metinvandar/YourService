package com.metinvandar.yourservice.view.detail

import com.metinvandar.yourservice.domain.models.ServiceDetailedItem
import com.metinvandar.yourservice.view.UIState

data class DetailUIState(
    override val isConnected: Boolean = true,
    override val loading: Boolean = false,
    override val error: Boolean = false,
    val serviceItem: ServiceDetailedItem? = null,
): UIState
