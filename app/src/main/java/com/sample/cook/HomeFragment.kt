package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import com.sample.cook.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this

        val viewPager = binding.homeViewPager.apply {
            adapter = HomePagerAdapter(childFragmentManager)
        }

        binding.homeTabLayout.setupWithViewPager(viewPager)

        binding.homeFab.setOnClickListener {
            val direction = HomeFragmentDirections.actionNavigationHomeToFragmentRecipeCreate()
            findNavController().navigate(direction)
        }

        return binding.root
    }


}

class HomePagerAdapter(supportFragmentManager: FragmentManager) :
    FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> RecipeListFragment.newInstance(RECIPE_TYPE_FAMILY)
        1 -> RecipeListFragment.newInstance(RECIPE_TYPE_VEGETARIAN)
        else -> RecipeListFragment.newInstance(RECIPE_TYPE_MINE)
    }

    override fun getPageTitle(position: Int): CharSequence? = when (position) {
        0 -> "Friendly"
        1 -> "Vegetarian"
        else -> "My recipes"
    }

    override fun getCount(): Int = 3

    companion object {
        const val RECIPE_TYPE_FAMILY = "family-friendly"
        const val RECIPE_TYPE_VEGETARIAN = "vegetarian"
        const val RECIPE_TYPE_MINE = "mine"
    }
}