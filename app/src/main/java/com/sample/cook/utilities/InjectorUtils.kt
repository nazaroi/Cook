package com.sample.cook.utilities

import android.content.Context
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.RecipeRepository
import com.sample.cook.viewmodels.FavoriteViewModelFactory
import com.sample.cook.viewmodels.RecipeCreateViewModelFactory
import com.sample.cook.viewmodels.RecipeDetailViewModelFactory
import com.sample.cook.viewmodels.RecipeListViewModelFactory

object InjectorUtils {

    private fun getRecipeRepository(context: Context): RecipeRepository {
        val dao = AppDatabase.getInstance(context.applicationContext).recipeDao()
        return RecipeRepository.getInstance(dao)
    }

    fun provideRecipeCreateViewModelFactory(context: Context): RecipeCreateViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeCreateViewModelFactory(repository)
    }

    fun provideRecipeListViewModelFactory(
        context: Context,
        type: String
    ): RecipeListViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeListViewModelFactory(repository, type)
    }

    fun provideRecipeDetailViewModelFactory(
        context: Context,
        id: String
    ): RecipeDetailViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeDetailViewModelFactory(repository, id)
    }

    fun provideFavoriteViewModelFactory(context: Context): FavoriteViewModelFactory {
        val repository = getRecipeRepository(context)
        return FavoriteViewModelFactory(repository)
    }
}