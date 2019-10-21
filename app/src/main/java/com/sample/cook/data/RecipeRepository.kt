package com.sample.cook.data

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext

/**
 * Repository module for handling data operations.
 */
class RecipeRepository private constructor(private val recipeDao: RecipeDao) {

    fun getRecipes() = recipeDao.getRecipes()

    fun getRecipesByIds(ids: Set<String>) = recipeDao.getRecipesByIds(ids)

    fun getRecipesByType(type: String) = recipeDao.getRecipesByType(type)

    fun getRecipe(recipeId: String) = recipeDao.getRecipe(recipeId)

    fun getFavoriteRecipes() = recipeDao.getFavoriteRecipes()

    suspend fun update(recipe: Recipe) {
        withContext(IO) {
            recipeDao.update(recipe)
        }
    }

    suspend fun delete(recipe: Recipe) {
        withContext(IO) {
            recipeDao.delete(recipe)
        }
    }

    suspend fun insert(recipe: Recipe) {
        withContext(IO) {
            recipeDao.insert(recipe)
        }
    }

    companion object {

        private var instance: RecipeRepository? = null

        fun getInstance(recipeDao: RecipeDao) =
            instance ?: RecipeRepository(recipeDao).also { instance = it }
    }
}