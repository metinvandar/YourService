package com.metinvandar.yourservice

import com.metinvandar.yourservice.data.Resource
import com.metinvandar.yourservice.data.repository.HomeRepository
import com.metinvandar.yourservice.domain.models.HomeData
import com.metinvandar.yourservice.domain.models.PostItem
import com.metinvandar.yourservice.domain.models.ServiceItem
import com.metinvandar.yourservice.domain.models.ServiceItemType
import com.metinvandar.yourservice.view.home.HomeViewModel
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
class HomeViewModelTest : BaseTest() {

    @Mock
    private lateinit var repository: HomeRepository

    private lateinit var viewModel: HomeViewModel

    @Test
    fun get_initial_home_data_success() = runBlockingTest {
        val expected = mockHomeData()
        `when`(repository.getHomeData())
            .thenReturn(expected)
        viewModel = HomeViewModel(repository)

        verify(repository).getHomeData()
        val stateResult = viewModel.uiState.value
        assert(!stateResult.error)
        val expectedResult = expected as Resource.Success
        assert(stateResult.homeData.services.size == expectedResult.data.services.size)
        assert(stateResult.homeData.popularServices.size == expectedResult.data.popularServices.size)
        assert(stateResult.homeData.posts.size == expectedResult.data.posts.size)
    }

    @Test
    fun get_initial_home_data_error() = runBlockingTest {
        `when`(repository.getHomeData())
            .thenReturn(Resource.Error(isNetworkError = false, errorCode = 100, errorBody = null))

        viewModel = HomeViewModel(repository)
        verify(repository).getHomeData()
        val stateResult = viewModel.uiState.value
        assert(stateResult.error)
        assert(stateResult.isConnected)

    }

    @Test
    fun get_initial_home_data_with_network_error() = runBlockingTest {
        `when`(repository.getHomeData())
            .thenReturn(Resource.Error(isNetworkError = true, errorCode = 100, errorBody = null))

        viewModel = HomeViewModel(repository)
        verify(repository).getHomeData()
        val stateResult = viewModel.uiState.value
        assert(stateResult.error)
        assert(!stateResult.isConnected)

    }

    private fun mockHomeData(): Resource<HomeData> {
        val services = ArrayList<ServiceItem>().apply {
            add(
                ServiceItem(
                    id = 1,
                    name = "",
                    type = ServiceItemType.HEALTH_EDUCATION,
                    imageUrl = ""
                )
            )
            add(
                ServiceItem(
                    id = 2,
                    name = "",
                    type = ServiceItemType.CLEANING,
                    imageUrl = ""
                )
            )
            add(
                ServiceItem(
                    id = 3,
                    name = "",
                    type = ServiceItemType.MODIFICATION,
                    imageUrl = ""
                )
            )
        }

        val postList = ArrayList<PostItem>().apply {
            add(PostItem(title = "", category = "", imageUrl = "", link = ""))
            add(PostItem(title = "", category = "", imageUrl = "", link = ""))
            add(PostItem(title = "", category = "", imageUrl = "", link = ""))
        }

        return Resource.Success(
            HomeData(
                services = services,
                popularServices = services,
                posts = postList
            )
        )
    }
}
