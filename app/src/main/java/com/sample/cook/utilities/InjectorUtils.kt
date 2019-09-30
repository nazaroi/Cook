package com.sample.cook.utilities

import android.content.Context
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.RecipeRepository
import com.sample.cook.viewmodels.RecipesViewModelFactory

object InjectorUtils {

    private fun getRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(AppDatabase.getInstance(context.applicationContext).recipeDao())
    }

    fun provideRecipeListViewModelFactory(context: Context): RecipesViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipesViewModelFactory(repository)
    }
}