package com.metinvandar.yourservice

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.repository.DetailRepository
import com.metinvandar.yourservice.domain.models.ServiceDetailedItem
import com.metinvandar.yourservice.view.detail.DetailViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.argumentCaptor
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest: BaseTest() {

    @Mock
    private lateinit var repository: DetailRepository

    private lateinit var viewModel: DetailViewModel

    @Test
    fun get_detail_data_success() = runBlockingTest {
        val expected = mockDetailData()
        val serviceId = 100
        val idCaptor = argumentCaptor<Int>()

        viewModel = DetailViewModel(repository)
        `when`(repository.getServiceDetail(serviceId))
            .thenReturn(expected)
        viewModel.getDetail(serviceId)

        verify(repository).getServiceDetail(idCaptor.capture())
        assert(idCaptor.firstValue == serviceId)

        val stateResult = viewModel.uiState.value
        assert(!stateResult.error)
        val expectedResult = expected as Resource.Success
        assert(stateResult.serviceItem!!.id == expectedResult.data.id)
    }

    @Test
    fun get_detail_data_error() = runBlockingTest {
        val serviceId = 100
        viewModel = DetailViewModel(repository)
        `when`(repository.getServiceDetail(serviceId))
            .thenReturn(Resource.Error(isNetworkError = false, errorCode = 100, errorBody = null))
        viewModel.getDetail(serviceId)

        verify(repository).getServiceDetail(serviceId)
        val stateResult = viewModel.uiState.value
        assert(stateResult.error)
        assert(stateResult.isConnected)
    }

    @Test
    fun get_detail_data_with_network_error() = runBlockingTest {
        val serviceId = 100
        viewModel = DetailViewModel(repository)
        `when`(repository.getServiceDetail(serviceId))
            .thenReturn(Resource.Error(isNetworkError = true, errorCode = 100, errorBody = null))
        viewModel.getDetail(serviceId)

        verify(repository).getServiceDetail(serviceId)
        val stateResult = viewModel.uiState.value
        assert(stateResult.error)
        assert(!stateResult.isConnected)
    }


    private fun mockDetailData(): Resource<ServiceDetailedItem> {
        return Resource.Success(
            ServiceDetailedItem(
                id = 1,
                name = "",
                longName = "",
                imageUrl = "",
                completedJobs = 0,
                averageRating = 0.0,
                proCount = 0
            )
        )
    }
}