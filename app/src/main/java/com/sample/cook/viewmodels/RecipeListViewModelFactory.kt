package com.sample.cook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.cook.data.RecipeRepository

/**
 * Factory for creating a [RecipeListViewModel] with a constructor that takes a
 * [RecipeRepository].
 */
class RecipeListViewModelFactory(private val repository: RecipeRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeListViewModelFactory(repository) as T
    }
}