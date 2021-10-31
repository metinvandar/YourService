package com.metinvandar.yourservice.view.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.metinvandar.yourservice.R
import com.metinvandar.yourservice.databinding.ServiceItemBinding
import com.metinvandar.yourservice.domain.models.ServiceItem
import com.metinvandar.yourservice.domain.models.ServiceItemType

class AllServicesAdapter(
    private val itemClickListener: ServiceItemClickListener
) : ListAdapter<ServiceItem, AllServicesAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ServiceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val itemBinding: ServiceItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: ServiceItem) {
            itemBinding.root.setOnClickListener {
                itemClickListener.onItemClick(item.id)
            }
            val itemName = when (item.type) {
                ServiceItemType.WEDDING_ORGANIZATION -> {
                    itemView.context.getString(R.string.home_wedding_service_short_name)
                }
                ServiceItemType.HEALTH_EDUCATION -> {
                    itemView.context.getString(R.string.home_health_education_short_name)
                }
                else -> item.type.value
            }
            itemBinding.tvServiceItemName.text = itemName
            Glide.with(itemBinding.root)
                .load(item.type.image(itemView.context))
                .transition(withCrossFade())
                .into(itemBinding.imageViewService)
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