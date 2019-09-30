package com.sample.cook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository

class RecipesViewModel(recipeRepository: RecipeRepository) : ViewModel() {
    val recipes: LiveData<List<Recipe>> = recipeRepository.getRecipes()
}