package com.sample.cook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sample.cook.databinding.ItemBackgroundImageBinding
import com.sample.cook.viewmodels.BackgroundImageViewModel

class BackgroundImageAdapter(val viewModel: BackgroundImageViewModel) :
    RecyclerView.Adapter<BackgroundImageAdapter.BackgroundImageViewHolder>() {

    override fun getItemCount(): Int = viewModel.imageResourceIds.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBackgroundImageBinding.inflate(inflater, parent, false)
        return BackgroundImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackgroundImageViewHolder, position: Int) =
        holder.bind(position)

    private fun createOnItemClickListener(position: Int) = View.OnClickListener {
        viewModel.currImageIndex.value = position
        notifyDataSetChanged()
    }

    inner class BackgroundImageViewHolder(private val binding: ItemBackgroundImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) =
            with(binding) {
                isSelected = viewModel.currImageIndex.value == position
                resId = viewModel.imageResourceIds.value?.get(position) ?: 0
                onItemClick = createOnItemClickListener(position)
                executePendingBindings()
            }
    }
}