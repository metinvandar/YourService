package com.metinvandar.yourservice.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.repository.DetailRepository
import com.metinvandar.yourservice.domain.models.ServiceDetailedItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: DetailRepository): ViewModel() {

    private val _uiState = MutableStateFlow(DetailUIState())
    val uiState = _uiState.asStateFlow()

    fun getDetail(serviceId: Int) {
        viewModelScope.launch {
            _uiState.value = uiState.value.copy(loading = true, error = false, isConnected = true)

            when(val result = repository.getServiceDetail(serviceId)) {
                is Resource.Success -> {
                    handleSuccess(result.data)
                }
                is Resource.Error -> {
                    handleError(result)
                }
            }
        }
    }

    private fun handleSuccess(serviceItem: ServiceDetailedItem) {
        _uiState.value = uiState.value.copy(
            error = false,
            isConnected = true,
            loading = false,
            serviceItem = serviceItem
        )
    }

    private fun handleError(error: Resource.Error) {
        _uiState.value = uiState.value.copy(
            error = true,
            loading = false,
            isConnected = error.isNetworkError.not()
        )
    }
}
