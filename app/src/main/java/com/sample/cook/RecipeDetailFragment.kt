package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.appbar.AppBarLayout
import com.sample.cook.databinding.FragmentRecipeDetailBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.RecipeDetailViewModel
import timber.log.Timber

class RecipeDetailFragment : Fragment() {

    private val args: RecipeDetailFragmentArgs by navArgs()

    private val viewModel: RecipeDetailViewModel by viewModels {
        InjectorUtils.provideRecipeDetailViewModelFactory(requireContext(), args.recipeId)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

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

        binding.detailFab.setOnClickListener {
            viewModel.toggleFavorite()
        }

        viewModel.recipe.observe(viewLifecycleOwner) { recipe ->

            binding.recipe = recipe

            binding.detailFab.run {
                val resId = if (recipe.isFavorite) R.color.colorRed else R.color.white
                val color = ContextCompat.getColor(requireContext(), resId)
                DrawableCompat.setTint(drawable, color)
            }
        }

        return binding.root
    }
}