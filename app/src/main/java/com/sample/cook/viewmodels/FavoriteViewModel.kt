package com.sample.cook.viewmodels

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.sample.cook.FavoriteFragmentDirections
import com.sample.cook.handlers.RecipeHandler
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(val repository: RecipeRepository) : ViewModel(),
    RecipeHandler {

    var favoriteRecipes = repository.getFavoriteRecipes()

    override fun openDetail(view: View, recipe: Recipe) {
        val direction =
            FavoriteFragmentDirections.actionNavigationFavoriteToRecipeDetailFragment(recipe.id)
        view.findNavController().navigate(direction)
    }

    override fun remove(recipe: Recipe) {
        viewModelScope.launch {
            repository.delete(recipe)
        }
    }
}