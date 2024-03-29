package com.sample.cook.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.cook.data.RecipeRepository

class RecipeDetailViewModelFactory(
    private val repository: RecipeRepository,
    private val id: String
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeDetailViewModel(repository, id) as T
    }
}