package com.metinvandar.yourservice

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.model.HomeResponse
import com.metinvandar.yourservice.data.model.Post
import com.metinvandar.yourservice.data.model.Service
import com.metinvandar.yourservice.data.repository.HomeRepository
import com.metinvandar.yourservice.data.repository.HomeRepositoryImp
import com.metinvandar.yourservice.data.service.Api
import com.metinvandar.yourservice.domain.mappers.PostItemMapper
import com.metinvandar.yourservice.domain.mappers.ServiceItemMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeRepositoryTest: BaseTest() {

    @Mock
    private lateinit var api: Api

    private val serviceItemMapper = ServiceItemMapper()
    private val postItemMapper = PostItemMapper()

    private lateinit var repository: HomeRepository

    @Test
    fun get_home_data_success() = runBlockingTest {
        repository = HomeRepositoryImp(api, serviceItemMapper, postItemMapper)
        val expected = mockApiResponse()
        `when`(api.homeData())
            .thenReturn(expected)
        val result =  repository.getHomeData()
        verify(api).homeData()
        assert(result is Resource.Success)
        val successResult = result as Resource.Success
        assert(successResult.data.services.size == expected.allServices.size)
        assert(successResult.data.popularServices.size == expected.popular.size)
        assert(successResult.data.posts.size == expected.posts.size)

    }

    @Test
    fun get_home_data_error() = runBlockingTest {
        repository = HomeRepositoryImp(api, serviceItemMapper, postItemMapper)
        val expected = RuntimeException()

        `when`(api.homeData())
            .thenThrow(expected)
        val result = repository.getHomeData()
        verify(api).homeData()
        assert(result is Resource.Error)
    }

    private fun mockApiResponse(): HomeResponse {
        val serviceList = ArrayList<Service>().apply {
            add(Service(
                id = 1,
                serviceId = 1,
                name = "",
                type = "",
                imageUrl = ""
            ))
            add(Service(
                id = 2,
                serviceId = 2,
                name = "",
                type = "",
                imageUrl = ""
            ))
            add(Service(
                id = 3,
                serviceId = 3,
                name = "",
                type = "",
                imageUrl = ""
            ))
        }

        val postList = ArrayList<Post>().apply {
            add(Post(title = "", category = "", imageUrl = "", link = ""))
            add(Post(title = "", category = "", imageUrl = "", link = ""))
        }

        return HomeResponse(
            allServices = serviceList,
            popular = serviceList,
            posts = postList
        )
    }
}