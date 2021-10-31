package com.metinvandar.yourservice.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.metinvandar.yourservice.databinding.PopularServicesItemBinding
import com.metinvandar.yourservice.domain.models.ServiceItem

class PopularListAdapter(private val itemClickListener: ServiceItemClickListener): ListAdapter<ServiceItem, PopularListAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = PopularServicesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: PopularServicesItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ServiceItem) {
            itemBinding.root.setOnClickListener {
                itemClickListener.onItemClick(item.id)
            }
            itemBinding.textViewPopularItemName.text = item.name
            Glide.with(itemBinding.root)
                .load(item.imageUrl)
                .transition(withCrossFade())
                .centerCrop()
                .into(itemBinding.imageVewPopularListItem)
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<ServiceItem>() {
        override fun areItemsTheSame(oldItem: ServiceItem, newItem: ServiceItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ServiceItem, newItem: ServiceItem): Boolean {
            return oldItem.name == newItem.name
        }

    }
}
