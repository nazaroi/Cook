package com.sample.cook.utilities

import android.content.Context
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.RecipeRepository
import com.sample.cook.viewmodels.RecipesTypeViewModelFactory
import com.sample.cook.viewmodels.RecipeCreateViewModelFactory

object InjectorUtils {

    private fun getRecipeRepository(context: Context): RecipeRepository {
        return RecipeRepository.getInstance(AppDatabase.getInstance(context.applicationContext).recipeDao())
    }

    fun provideRecipeCreateViewModelFactory(context: Context): RecipeCreateViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeCreateViewModelFactory(repository)
    }

    fun provideRecipesTypeViewModelFactory(
        context: Context,
        type: String
    ): RecipesTypeViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipesTypeViewModelFactory(repository, type)
    }
}