package com.sample.cook.data

/**
 * Repository module for handling data operations.
 */
class RecipeRepository private constructor(private val recipeDao: RecipeDao) {

    fun getRecipes() = recipeDao.getRecipes()

    fun getRecipe(recipeId: String) = recipeDao.getRecipe(recipeId)

    companion object {

        private var instance: RecipeRepository? = null

        fun getInstance(recipeDao: RecipeDao) =
            instance ?: RecipeRepository(recipeDao)
    }
}