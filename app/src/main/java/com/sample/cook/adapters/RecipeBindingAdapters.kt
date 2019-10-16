package com.sample.cook.adapters

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sample.cook.R
import com.sample.cook.utilities.removeFromStringSet
import com.sample.cook.viewmodels.RecipeFavoriteViewModel.Companion.APP_PREFERENCE_FILE
import com.sample.cook.viewmodels.RecipeFavoriteViewModel.Companion.FAVORITE_IDS_KEY

@BindingAdapter("recipeImageUri")
fun bindRecipeImageUri(view: ImageView, imageUri: String?) {
    if (imageUri.isNullOrEmpty()) {
        view.setImageResource(R.drawable.test)
    } else {
        Glide.with(view.context).load(imageUri).into(view)
    }
}

@BindingAdapter("onRemoveFavorite")
fun bindOnRemoveFavorite(view: ImageView, id: String?) {
    if (!id.isNullOrEmpty()) {
        view.setOnClickListener {
            val pref = view.context.getSharedPreferences(APP_PREFERENCE_FILE, Context.MODE_PRIVATE)
            pref.removeFromStringSet(FAVORITE_IDS_KEY, id)
        }
    }
}