package com.metinvandar.yourservice.view.home.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalSpaceDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.right = space
        } else {
            outRect.right = space
            outRect.left = space
        }
    }
}