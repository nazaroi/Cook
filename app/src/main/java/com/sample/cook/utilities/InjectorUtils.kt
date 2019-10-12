package com.sample.cook.utilities

import android.app.Application
import android.content.Context
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.RecipeRepository
import com.sample.cook.viewmodels.FavoriteViewModelFactory
import com.sample.cook.viewmodels.RecipeCreateViewModelFactory
import com.sample.cook.viewmodels.RecipeViewModelFactory
import com.sample.cook.viewmodels.RecipesTypeViewModelFactory

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

    fun provideRecipeViewModelFactory(context: Context, id: String): RecipeViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeViewModelFactory(repository, id)
    }

    fun provideFavoriteViewModelFactory(
        context: Context,
        application: Application
    ): FavoriteViewModelFactory {
        val repository = getRecipeRepository(context)
        return FavoriteViewModelFactory(repository, application)
    }
}