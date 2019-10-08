package com.sample.cook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository
import org.jetbrains.anko.doAsync

class RecipeCreateViewModel(repository: RecipeRepository) : ViewModel() {
    private val newRecipe = MutableLiveData<Recipe>()

    init {
        newRecipe.observeForever {
            doAsync { repository.insert(it) }
        }
    }

    fun setNewRecipe(_newRecipe: Recipe) {
        newRecipe.value = _newRecipe
    }
}