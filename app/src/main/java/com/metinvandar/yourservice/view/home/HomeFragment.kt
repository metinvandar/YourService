package com.metinvandar.yourservice.view.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.metinvandar.yourservice.R
import com.metinvandar.yourservice.databinding.FragmentHomeBinding
import com.metinvandar.yourservice.utils.showErrorView
import com.metinvandar.yourservice.view.home.adapter.*
import com.metinvandar.yourservice.view.home.decoration.GridSpaceItemDecoration
import com.metinvandar.yourservice.view.home.decoration.HorizontalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ServiceItemClickListener,
    PostItemClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var allServicesAdapter: AllServicesAdapter
    private lateinit var popularListAdapter: PopularListAdapter
    private lateinit var postAdapter: PostsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)
        initAllServicesList()
        initPopularServicesList()
        initPostsList()
        collectUIState()
    }

    private fun collectUIState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                renderUIState(state = it)
            }
        }
    }

    private fun renderUIState(state: HomeUIState) {
        when {
            state.error -> {
                binding.apply {
                    nsvHome.visibility = View.GONE
                    progressBarHome.visibility = View.GONE
                }
                showErrorView(state)
            }
            state.loading -> {
                binding.apply {
                    nsvHome.visibility = View.GONE
                    progressBarHome.visibility = View.VISIBLE
                }
            }
            else -> {
                allServicesAdapter.submitList(state.homeData.services)
                popularListAdapter.submitList(state.homeData.popularServices)
                postAdapter.submitList(state.homeData.posts)
                binding.apply {
                    nsvHome.visibility = View.VISIBLE
                    progressBarHome.visibility = View.GONE
                }
            }
        }
    }

    private fun initAllServicesList() {
        allServicesAdapter = AllServicesAdapter(this)
        val gridLayoutManager = GridLayoutManager(requireContext(), 4)
        val itemDecoration = GridSpaceItemDecoration(10)
        binding.recyclerViewAllServices.apply {
            layoutManager = gridLayoutManager
            addItemDecoration(itemDecoration)
            adapter = allServicesAdapter
        }
    }

    private fun initPopularServicesList() {
        popularListAdapter = PopularListAdapter(this)
        val horizontalLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        val itemDecoration = HorizontalSpaceDecoration(10)
        binding.recyclerViewPopularServices.apply {
            layoutManager = horizontalLayoutManager
            addItemDecoration(itemDecoration)
            adapter = popularListAdapter
        }
    }

    private fun initPostsList() {
        postAdapter = PostsAdapter(this)
        val horizontalLayoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        val itemDecoration = HorizontalSpaceDecoration(10)
        binding.recyclerViewHomePosts.apply {
            layoutManager = horizontalLayoutManager
            addItemDecoration(itemDecoration)
            adapter = postAdapter
        }
    }

    override fun onItemClick(serviceId: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeToDetail(serviceId))
    }

    override fun onPostClick(link: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeToWebView(link))
    }
}
