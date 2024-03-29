package com.sample.cook.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.sample.cook.HomeFragmentDirections
import com.sample.cook.handlers.RecipeHandler
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(val repository: RecipeRepository, type: String) : ViewModel(),
    RecipeHandler {
    val typeRecipes: LiveData<List<Recipe>> = repository.getRecipesByType(type)


    override fun openDetail(view: View, recipe: Recipe) {
        val direction = HomeFragmentDirections.actionNavigationHomeToRecipeDetailFragment(recipe.id)
        view.findNavController().navigate(direction)
    }

    override fun remove(recipe: Recipe) {
        viewModelScope.launch {
            repository.delete(recipe)
        }
    }
}