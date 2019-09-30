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
import com.sample.cook.viewmodels.RecipesViewModel

class RecipeListFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels {
        InjectorUtils.provideRecipeListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeListBinding.inflate(inflater, container, false)

        val adapter = RecipeAdapter()

        binding.recipeList.adapter = adapter

        subscribeUi(adapter, binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }

    private fun subscribeUi(adapter: RecipeAdapter, binding: FragmentRecipeListBinding) {
        viewModel.recipes.observe(viewLifecycleOwner) { list ->
            if (!list.isNullOrEmpty()) adapter.submitList(list)
        }
    }

    companion object {
        fun newInstance(type: String): RecipeListFragment {
            return RecipeListFragment().apply {
                arguments = bundleOf("recipe_type" to type)
            }
        }
    }
}