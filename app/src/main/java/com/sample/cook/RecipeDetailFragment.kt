package com.sample.cook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.sample.cook.databinding.FragmentRecipeDetailBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.utilities.addToStringSet
import com.sample.cook.viewmodels.RecipeFavoriteViewModel.Companion.APP_PREFERENCE_FILE
import com.sample.cook.viewmodels.RecipeFavoriteViewModel.Companion.FAVORITE_IDS_KEY
import com.sample.cook.viewmodels.RecipeViewModel
import timber.log.Timber


class RecipeDetailFragment : Fragment() {

    private lateinit var recipeId: String

    private lateinit var preferences: SharedPreferences

    private val viewModel: RecipeViewModel by viewModels {
        InjectorUtils.provideRecipeViewModelFactory(requireContext(), recipeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        recipeId = requireArguments().get("recipe_id") as String
        preferences = requireContext()
            .getSharedPreferences(APP_PREFERENCE_FILE, Context.MODE_PRIVATE)

        val binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)

        preferences.getStringSet(FAVORITE_IDS_KEY, setOf())?.let {
            if (it.contains(recipeId)) binding.detailFab.hide()
        }

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.appbar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {

            var isShow = true
            var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                // true for first call only
                if (scrollRange == -1) scrollRange = appBarLayout.totalScrollRange

                if (scrollRange + verticalOffset == 0) {
                    binding.toolbar.title = viewModel.recipe.value?.name
                    isShow = true
                } else if (isShow) {
                    binding.toolbar.title = null
                    isShow = false
                }
            }
        })

        binding.detailFab.setOnClickListener { view ->

            preferences.addToStringSet(FAVORITE_IDS_KEY, recipeId)

            view.visibility = View.GONE
        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            binding.recipe = recipe
        }
        return binding.root
    }
}