package com.sample.cook

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sample.cook.HomePagerAdapter.Companion.RECIPE_TYPE_MINE
import com.sample.cook.data.Recipe
import com.sample.cook.databinding.FragmentRecipeCreateBinding
import com.sample.cook.utilities.InjectorUtils
import com.sample.cook.utilities.toUnderscore
import com.sample.cook.viewmodels.RecipeCreateViewModel

class RecipeCreateFragment : Fragment() {

    private lateinit var binding: FragmentRecipeCreateBinding

    private val viewModel: RecipeCreateViewModel by viewModels {
        InjectorUtils.provideRecipeCreateViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentRecipeCreateBinding.inflate(inflater, container, false)

        binding.photoLoader.setOnClickListener {
            photoFromGallery()
        }

        binding.createNewRecipe.setOnClickListener {
            if (validRecipe()) {

                viewModel.run {
                    val name = binding.nameInputLayout.editText?.text.toString()
                    val description = binding.descriptionInputLayout.editText?.text.toString()
                    val imageUri = binding.photoLoader.tag?.toString()
                    val id = name.toUnderscore()

                    insert(Recipe(id, name, description, imageUri, RECIPE_TYPE_MINE))
                }

                findNavController().navigateUp()
            }
        }
        return binding.root
    }

    private fun validRecipe(): Boolean {

        val name = binding.nameInputLayout.editText?.text.toString()
        if (name.isBlank()) {
            binding.nameInputLayout.error = "Enter recipe name"
        }

        val description = binding.descriptionInputLayout.editText?.text.toString()
        if (description.isBlank()) {
            binding.descriptionInputLayout.error = "Enter recipe description"
        }

        if (name.isBlank() || description.isBlank()) return false

        return true
    }

    private fun photoFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, PHOTO_PICK_REQUEST_CODE)
    }

    override fun onActivityResult(reqCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(reqCode, resultCode, intent)
        if (resultCode == RESULT_OK && reqCode == PHOTO_PICK_REQUEST_CODE) {
            intent?.data?.let { uri ->
                binding.imageUri = uri.toString()
                binding.photoLoader.tag = uri
            }
        }
    }

    companion object {
        private const val PHOTO_PICK_REQUEST_CODE = 0
    }
}