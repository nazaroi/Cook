package com.sample.cook.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.cook.data.RecipeRepository

class RecipeFavoriteViewModelFactory(
    private val repository: RecipeRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecipeFavoriteViewModel(repository, application) as T
    }
}