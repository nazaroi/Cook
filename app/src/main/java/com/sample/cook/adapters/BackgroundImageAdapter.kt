package com.sample.cook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.sample.cook.databinding.ItemSettingsBackgroundImageBinding
import com.sample.cook.viewmodels.BackgroundImageViewModel

class BackgroundImageAdapter(val viewModel: BackgroundImageViewModel) :
    RecyclerView.Adapter<BackgroundImageAdapter.BackgroundImageViewHolder>() {

    override fun getItemCount(): Int = viewModel.imageResourceIds.value?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSettingsBackgroundImageBinding.inflate(inflater, parent, false)
        return BackgroundImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackgroundImageViewHolder, position: Int) {
        viewModel.imageResourceIds.value?.get(position)?.let { resId ->
            with(holder) {
                val onItemClick = createOnItemClickListener(position)
                val isSelected = viewModel.currImageIndex.value == position
                bind(onItemClick, resId, isSelected)
            }
        }
    }

    private fun createOnItemClickListener(selectedImageIndex: Int): View.OnClickListener {
        return View.OnClickListener {
            viewModel.currImageIndex.value = selectedImageIndex
            notifyDataSetChanged()
        }
    }

    inner class BackgroundImageViewHolder(private val binding: ItemSettingsBackgroundImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(_onItemClick: View.OnClickListener, resId: Int, isSelected: Boolean) =
            with(binding) {
                item = BackgroundImageItem(resId, isSelected)
                onItemClick = _onItemClick
                executePendingBindings()
            }
    }
}

class BackgroundImageItem(_resId: Int, _isSelected: Boolean) : ViewModel() {

    val resId: ObservableInt = ObservableInt()
    val isSelected: ObservableBoolean = ObservableBoolean()

    init {
        resId.set(_resId)
        isSelected.set(_isSelected)
    }
}