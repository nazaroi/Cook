package com.sample.cook.data

/**
 * Repository module for handling data operations.
 */
class RecipeRepository private constructor(private val recipeDao: RecipeDao) {

    fun getRecipes() = recipeDao.getRecipes()

    fun getRecipesByIds(ids: Set<String>) = recipeDao.getRecipesByIds(ids)

    fun getRecipesByType(type: String) = recipeDao.getRecipesByType(type)

    fun getRecipe(recipeId: String) = recipeDao.getRecipe(recipeId)

    fun insert(recipe: Recipe) = recipeDao.insert(recipe)

    companion object {

        private var instance: RecipeRepository? = null

        fun getInstance(recipeDao: RecipeDao) =
            instance ?: RecipeRepository(recipeDao).also { instance = it }
    }
}