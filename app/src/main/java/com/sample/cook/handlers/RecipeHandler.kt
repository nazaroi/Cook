package com.sample.cook.handlers

import android.view.View
import com.sample.cook.data.Recipe

interface RecipeHandler {
    fun openDetail(view: View, recipe: Recipe)
    fun remove(recipe: Recipe)
}