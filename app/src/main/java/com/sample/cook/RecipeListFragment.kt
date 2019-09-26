package com.sample.cook


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sample.cook.databinding.FragmentRecipeListBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.RecipeListViewModel

class RecipeListFragment : Fragment() {

    private val viewModel: RecipeListViewModel by viewModels {
        InjectorUtils.provideRecipeListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRecipeListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    }
}
