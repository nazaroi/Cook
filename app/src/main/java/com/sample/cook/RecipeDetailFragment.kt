package com.sample.cook

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.sample.cook.databinding.FragmentRecipeDetailBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.FavoriteViewModel.Companion.APP_PREFERENCE_FILE
import com.sample.cook.viewmodels.FavoriteViewModel.Companion.FAVORITE_IDS_KEY
import com.sample.cook.viewmodels.RecipeViewModel

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


        binding.detailFab.setOnClickListener { view ->

            val newIds = (preferences.getStringSet(FAVORITE_IDS_KEY, setOf()) ?: setOf())
                .toMutableSet()
                .apply { add(recipeId) }

            preferences.edit {
                putStringSet(FAVORITE_IDS_KEY, newIds)
            }

            view.visibility = View.GONE
        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->
            binding.recipe = recipe
        }

        return binding.root
    }
}