package com.metinvandar.yourservice.view.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.metinvandar.yourservice.R
import com.metinvandar.yourservice.databinding.FragmentDetailBinding
import com.metinvandar.yourservice.utils.showErrorView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private var serviceId: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailBinding.bind(view)
        arguments?.let { bundle ->
            serviceId = bundle.getInt("serviceId")
            serviceId?.let { viewModel.getDetail(it) }
        }
        collectUIState()
    }

    private fun collectUIState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect {
                renderUI(it)
            }
        }
    }

    private fun renderUI(state: DetailUIState) {
        when {
            state.error -> {
                showErrorView(state) {
                    serviceId?.let { viewModel.getDetail(it) }
                }
                binding.apply {
                    progressBarDetail.visibility = View.GONE
                    scrollViewDetail.visibility = View.GONE
                }
            }
            state.loading -> {
                binding.apply {
                    progressBarDetail.visibility = View.VISIBLE
                    scrollViewDetail.visibility = View.GONE
                }
            }
            else -> {
                binding.apply {
                    textViewAverageStar.text = getString(
                        R.string.detail_average_rating,
                        state.serviceItem?.averageRating.toString()
                    )
                    textViewCompletedJobs.text =
                        getString(R.string.detail_job_completed, state.serviceItem?.completedJobs)
                    textViewProfNumber.text =
                        getString(R.string.detail_prof_number_near, state.serviceItem?.proCount)
                    textViewDetailTitle.text = state.serviceItem?.name
                    progressBarDetail.visibility = View.GONE
                    scrollViewDetail.visibility = View.VISIBLE

                    Glide.with(requireContext())
                        .load(state.serviceItem?.imageUrl)
                        .transition(withCrossFade())
                        .into(imageViewDetailTop)
                }
            }
        }
    }
}