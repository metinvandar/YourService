package com.metinvandar.yourservice.view.home

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.repository.HomeRepository
import com.metinvandar.yourservice.domain.models.HomeData
import com.metinvandar.yourservice.domain.models.PostItem
import com.metinvandar.yourservice.domain.models.ServiceItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUIState())
    val uiState = _uiState.asStateFlow()

    init {
        getHomeData()
    }

    private fun getHomeData() {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(
                isLoading = true
            )
            when (val homeResult = repository.getHomeData()) {
                is Resource.Success -> {
                    handleSuccess(homeResult.data.services, homeResult.data.posts, homeResult.data.popularServices)
                }
                is Resource.Error -> {
                    val errorMessage = if (homeResult.isNetworkError) {
                        "Check your internet connection"
                    } else {
                        "Something went wrong"
                    }
                    handleError(errorMessage)
                }
            }
        }
    }

    private fun handleSuccess(services: List<ServiceItem>, posts: List<PostItem>, popularServices: List<ServiceItem>) {
        _uiState.value = uiState.value.copy(
            isLoading = false,
            errorMessage = null,
            homeData = HomeData(
                services = services,
                popularServices = popularServices,
                posts = posts
            )
        )
    }

    private fun handleError(errorMessage: String) {
        _uiState.value = uiState.value.copy(
            isLoading = false,
            errorMessage = errorMessage
        )
    }
}
