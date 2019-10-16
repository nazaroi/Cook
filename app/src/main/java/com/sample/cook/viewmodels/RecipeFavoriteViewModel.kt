package com.sample.cook.viewmodels

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sample.cook.data.Recipe
import com.sample.cook.data.RecipeRepository
import org.jetbrains.anko.doAsync

class RecipeFavoriteViewModel(private val repository: RecipeRepository, application: Application) :
    AndroidViewModel(application) {

    var recipes = MutableLiveData<List<Recipe>>()

    private val listener = SharedPreferences.OnSharedPreferenceChangeListener { pref, keys ->
        if (keys == FAVORITE_IDS_KEY) loadFavorite(pref)
    }

    init {
        val pref = application.getSharedPreferences(APP_PREFERENCE_FILE, MODE_PRIVATE)
        pref.registerOnSharedPreferenceChangeListener(listener)

        loadFavorite(pref)
    }

    private fun loadFavorite(pref: SharedPreferences) {
        val ids: Set<String> = pref.getStringSet(FAVORITE_IDS_KEY, setOf()) ?: setOf()
        doAsync {
            recipes.postValue(repository.getRecipesByIds(ids))
        }
    }

    companion object {
        const val FAVORITE_IDS_KEY = "FAVORITE_IDS_KEY"
        const val APP_PREFERENCE_FILE = "APP_PREFERENCE_FILE"
    }
}