package com.sample.cook.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.cook.R

class BackgroundImageViewModel : ViewModel() {

    val imageResourceIds = MutableLiveData<IntArray>()

    val currImageIndex = MutableLiveData<Int>()

    init {
        imageResourceIds.value = intArrayOf(
            R.drawable.bg_app_1,
            R.drawable.bg_app_2,
            R.drawable.bg_app_3,
            R.drawable.bg_app_4,
            R.drawable.bg_app_5
        )

        currImageIndex.value = 0
    }


}