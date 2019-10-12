package com.sample.cook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository

class RecipeViewModel(recipeRepository: RecipeRepository, id: String) :
    ViewModel() {
    val recipe: LiveData<Recipe> = recipeRepository.getRecipe(id)
}