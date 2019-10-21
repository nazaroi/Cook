package com.sample.cook.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository
import kotlinx.coroutines.launch

class RecipeDetailViewModel(private val repository: RecipeRepository, val id: String) :
    ViewModel() {

    val recipe: LiveData<Recipe> = repository.getRecipe(id)

    fun toggleFavorite() {
        viewModelScope.launch {
            recipe.value?.let {
                repository.update(it.copy(isFavorite = !it.isFavorite))
            }
        }
    }

}

