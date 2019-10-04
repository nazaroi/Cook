package com.sample.cook.utilities

import android.content.Context
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.RecipeRepository
import com.sample.cook.viewmodels.RecipesTypeViewModelFactory
import com.sample.cook.viewmodels.RecipesViewModelFactory

object InjectorUtils {

    private fun getRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(AppDatabase.getInstance(context.applicationContext).recipeDao())
    }

    fun provideRecipesViewModelFactory(context: Context): RecipesViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipesViewModelFactory(repository)
    }

    fun provideRecipesTypeViewModelFactory(
        context: Context,
        type: String
    ): RecipesTypeViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipesTypeViewModelFactory(repository, type)
    }
}