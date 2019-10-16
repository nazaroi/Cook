package com.sample.cook.utilities

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.RecipeRepository
import com.sample.cook.viewmodels.*

object InjectorUtils {

     fun getRecipeRepository(context: Context): RecipeRepository {
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

    fun provideRecipeViewModelFactory(context: Context, id: String): RecipeViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeViewModelFactory(repository, id)
    }

    fun provideRecipeFavoriteViewModelFactory(
        context: Context,
        application: Application
    ): RecipeFavoriteViewModelFactory {
        val repository = getRecipeRepository(context)
        return RecipeFavoriteViewModelFactory(repository, application)
    }
}