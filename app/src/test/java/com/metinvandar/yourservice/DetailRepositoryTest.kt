package com.metinvandar.yourservice

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.model.DetailResponse
import com.metinvandar.yourservice.data.repository.DetailRepository
import com.metinvandar.yourservice.data.repository.DetailRepositoryImp
import com.metinvandar.yourservice.data.service.Api
import com.metinvandar.yourservice.domain.mappers.DetailMapper
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
class DetailRepositoryTest : BaseTest() {

    @Mock
    private lateinit var api: Api

    private val detailMapper = DetailMapper()

    private lateinit var repository: DetailRepository

    @Test
    fun get_detail_data_success() = runBlockingTest {
        repository = DetailRepositoryImp(api, detailMapper)
        val expected = mockDetailResponse()
        val serviceId = 10
        val idCaptor = argumentCaptor<Int>()
        `when`(api.detail(serviceId))
            .thenReturn(expected)
        val result = repository.getServiceDetail(serviceId)
        verify(api).detail(idCaptor.capture())
        assert(idCaptor.firstValue == serviceId)
        assert(result is Resource.Success)
        val successResult = result as Resource.Success
        assert(successResult.data.id == expected.id)
        assert(successResult.data.name == expected.name)
    }

    @Test
    fun get_detail_data_error() = runBlockingTest {
        repository = DetailRepositoryImp(api, detailMapper)
        val expected = RuntimeException()

        val serviceId = 100
        `when`(api.homeData())
            .thenThrow(expected)
        val result = repository.getServiceDetail(serviceId)

        verify(api).detail(serviceId)
        assert(result is Resource.Error)
    }

    private fun mockDetailResponse(): DetailResponse {
        return DetailResponse(
            id = 13,
            serviceId = 13,
            name = "Test Name",
            longName = "Test Long Name",
            imageUrl = "",
            averageRating = 3.0,
            proCount = 450,
            completedJob = 30,

            )
    }
}