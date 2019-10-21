package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.sample.cook.adapters.RecipeAdapter
import com.sample.cook.databinding.FragmentFavoriteBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.FavoriteViewModel

class FavoriteFragment : Fragment() {

    private val viewModel: FavoriteViewModel by viewModels {
        InjectorUtils.provideFavoriteViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val adapter = RecipeAdapter(viewModel)
        binding.recipeList.adapter = adapter

        subscribeUi(adapter, binding)

        return binding.root
    }


    private fun subscribeUi(adapter: RecipeAdapter, binding: FragmentFavoriteBinding) {
        viewModel.favoriteRecipes.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }
}
