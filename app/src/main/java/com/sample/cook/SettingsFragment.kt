package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.cook.adapters.BackgroundImageAdapter
import com.sample.cook.databinding.FragmentSettingsBinding
import com.sample.cook.viewmodels.BackgroundImageViewModel
import com.sample.cook.viewmodels.BackgroundImageViewModel.Companion.BACKGROUND_IMAGE_INDEX_PREFERENCE

class SettingsFragment : Fragment() {

    private lateinit var viewModel: BackgroundImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[BackgroundImageViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding.backgroundImagesRv.apply {
            adapter = BackgroundImageAdapter(viewModel)
            layoutManager = GridLayoutManager(requireContext(), 3)
        }

        viewModel.currImageIndex.observe(viewLifecycleOwner) {
            getDefaultSharedPreferences(context).edit {
                putInt(BACKGROUND_IMAGE_INDEX_PREFERENCE, it)
            }
        }

        return binding.root
    }
}