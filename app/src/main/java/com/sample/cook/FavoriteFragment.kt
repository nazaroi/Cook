package com.sample.cook

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.sample.cook.adapters.RecipeAdapter
import com.sample.cook.databinding.FragmentFavoriteBinding
import com.sample.cook.databinding.FragmentRecipeListBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.FavoriteViewModel
import com.sample.cook.viewmodels.RecipeViewModel

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels {
        InjectorUtils.provideFavoriteViewModelFactory(
            requireContext(), requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val adapter = RecipeAdapter()
        binding.recipeList.adapter = adapter

        subscribeUi(adapter, binding)

        return binding.root
    }

    private fun subscribeUi(adapter: RecipeAdapter, binding: FragmentFavoriteBinding) {
        viewModel.recipes.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) adapter.submitList(list)
        }
    }
}
