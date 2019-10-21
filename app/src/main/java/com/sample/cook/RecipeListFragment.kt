package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.sample.cook.adapters.RecipeAdapter
import com.sample.cook.databinding.FragmentRecipeListBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.RecipeListViewModel

class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels {
        val type = requireArguments().get("type") as String
        InjectorUtils.provideRecipeListViewModelFactory(requireContext(), type)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val adapter = RecipeAdapter(viewModel)
        binding.recipeList.adapter = adapter

        subscribeUi(adapter, binding)

        return binding.root
    }

    private fun subscribeUi(adapter: RecipeAdapter, binding: FragmentRecipeListBinding) {
        viewModel.typeRecipes.observe(viewLifecycleOwner) { list ->
            adapter.submitList(list)
        }
    }

    companion object {
        fun newInstance(type: String): RecipeListFragment {
            return RecipeListFragment().apply {
                arguments = bundleOf("type" to type)
            }
        }
    }
}