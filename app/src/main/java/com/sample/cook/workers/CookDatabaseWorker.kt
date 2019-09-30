package com.sample.cook.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.sample.cook.data.AppDatabase
import com.sample.cook.data.Recipe
import com.sample.cook.utilities.RECIPE_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class CookDatabaseWorker(context: Context, workerParams: WorkerParameters) :
    CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open(RECIPE_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val recipeType = object : TypeToken<List<Recipe>>() {}.type
                    val recipeList: List<Recipe> = Gson().fromJson(jsonReader, recipeType)

                    val db = AppDatabase.getInstance(applicationContext)
                    db.recipeDao().insertAll(recipeList)

                    Result.success()
                }
            }
        } catch (e: Exception) {
            Timber.e("Error cooking database: $e")
            Result.failure()
        }
    }
}