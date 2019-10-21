package com.sample.cook.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.sample.cook.R

class BackgroundImageViewModel(application: Application) : AndroidViewModel(application) {

    val imageResourceIds = MutableLiveData<IntArray>()

    val currImageIndex = MutableLiveData<Int>()

    init {
        imageResourceIds.value = intArrayOf(
            R.drawable.bg_app_0,
            R.drawable.bg_app_1,
            R.drawable.bg_app_2,
            R.drawable.bg_app_3,
            R.drawable.bg_app_4
        )

        currImageIndex.value =
            getDefaultSharedPreferences(application).getInt(BACKGROUND_IMAGE_INDEX_PREFERENCE, 0)
    }

    companion object {
        const val BACKGROUND_IMAGE_INDEX_PREFERENCE =
            "com.sample.cook.BACKGROUND_IMAGE_INDEX_PREFERENCE"
    }
}