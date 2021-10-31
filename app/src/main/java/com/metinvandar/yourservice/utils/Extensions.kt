package com.metinvandar.yourservice.utils

import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.metinvandar.yourservice.view.UIState

fun View.snackBar(message: String, action: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_INDEFINITE)
    action?.let {
        snackBar.setAction("Retry") {
            it()
            snackBar.dismiss()
        }
    }
    snackBar.show()
}

fun Fragment.showErrorView(uiState: UIState, retry: (() -> Unit)? = null) {
    if (uiState.isConnected.not()) {
        requireView().snackBar("Please check your internet connection", retry)
    } else {
        requireView().snackBar("Something went wrong!", retry)
    }
}
