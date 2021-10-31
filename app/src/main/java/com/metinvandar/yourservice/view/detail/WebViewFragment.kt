package com.metinvandar.yourservice.view.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.metinvandar.yourservice.R
import com.metinvandar.yourservice.databinding.FragmentWebViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    private var _binding: FragmentWebViewBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWebViewBinding.bind(view)
        arguments?.let { bundle ->
            val link = bundle.getString("link")
            binding.webViewDetail.webViewClient = WebViewClient()
            binding.webViewDetail.settings.javaScriptEnabled = true
            link?.let { binding.webViewDetail.loadUrl(it) }
        }
    }

}