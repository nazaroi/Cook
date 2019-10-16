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
import com.sample.cook.viewmodels.RecipeFavoriteViewModel

class FavoriteFragment : Fragment() {

    private val viewModel: RecipeFavoriteViewModel by viewModels {
        InjectorUtils.provideRecipeFavoriteViewModelFactory(
            requireContext(), requireActivity().application
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        val adapter = RecipeAdapter(requireContext())
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
