package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.viewModels
import com.sample.cook.databinding.FragmentHomeBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.viewmodels.RecipesViewModel

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewPager = binding.fragmentRecipeViewPager.apply {
            adapter = HomePagerAdapter(childFragmentManager)
        }

        binding.fragmentRecipeTabLayout.setupWithViewPager(viewPager)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}

class HomePagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> RecipeListFragment.newInstance("family-friendly")
        1 -> RecipeListFragment.newInstance("vegetarian")
        else -> RecipeListFragment.newInstance("my_recipes")
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> "Friendly"
        1 -> "Vegetarian"
        else -> "My recipes"
    }

    override fun getCount(): Int = 3
}