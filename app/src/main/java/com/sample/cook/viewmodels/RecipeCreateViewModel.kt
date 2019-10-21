package com.sample.cook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository
import kotlinx.coroutines.launch

class RecipeCreateViewModel(val repository: RecipeRepository) : ViewModel() {

    fun insert(recipe: Recipe) {
        viewModelScope.launch {
            repository.insert(recipe)
        }
    }
}