package com.sample.cook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.cook.data.RecipeRepository

class RecipeListViewModelFactory(
    private val repository: RecipeRepository,
    private val type: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeListViewModel(repository, type) as T
    }
}