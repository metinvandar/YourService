package com.metinvandar.yourservice.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.metinvandar.yourservice.databinding.PostItemBinding
import com.metinvandar.yourservice.domain.models.PostItem

class PostsAdapter(private val itemClickListener: PostItemClickListener) :
    ListAdapter<PostItem, PostsAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            PostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: PostItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: PostItem) {
            itemBinding.root.setOnClickListener {
                itemClickListener.onPostClick(item.link)
            }
            itemBinding.textViewPostItemTitle.text = item.title
            itemBinding.textViewPostItemCategory.text = item.category
            Glide.with(itemBinding.root)
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerCrop()
                .into(itemBinding.imageViewPostItem)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.title == newItem.title
        }

    }
}
