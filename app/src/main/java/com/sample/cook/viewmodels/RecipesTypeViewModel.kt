package com.sample.cook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository

class RecipesTypeViewModel(recipeRepository: RecipeRepository, type: String) :
    ViewModel() {
    val typeRecipes: LiveData<List<Recipe>> = recipeRepository.getRecipesByType(type)
}