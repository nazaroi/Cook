package com.sample.cook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.Recipe
import org.jetbrains.anko.doAsync
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree());

        val db = AppDatabase.getInstance(this)
        val recipeDao = db.recipeDao() // Dao's methods must be called asynchronously

        doAsync {
            val recipes = recipeDao.getRecipes()
            recipeDao.insert(Recipe("recipeId", "name", "description", "imageUrl"))
        }
    }
}