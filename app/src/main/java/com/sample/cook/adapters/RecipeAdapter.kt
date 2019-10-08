package com.sample.cook.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sample.cook.HomeFragmentDirections
import com.sample.cook.data.Recipe
import com.sample.cook.databinding.ItemRecipeBinding

class RecipeAdapter : ListAdapter<Recipe, RecipeAdapter.RecipeViewHolder>(RecipeDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        getItem(position).let { recipe ->
            with(holder) {
                bind(createOnClickListener(recipe.id), recipe)
            }
        }
    }

    private fun createOnClickListener(recipeId: String): View.OnClickListener {
        return View.OnClickListener {
            val direction = HomeFragmentDirections.actionNavigationHomeToRecipeDetailFragment(recipeId)
            it.findNavController().navigate(direction)
        }
    }

    inner class RecipeViewHolder(private val binding: ItemRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(clickListener: View.OnClickListener, recipe: Recipe) {
            binding.recipe = recipe
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}

object RecipeDiff : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean =
        oldItem == newItem
}