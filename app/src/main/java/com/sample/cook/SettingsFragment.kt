package com.sample.cook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.sample.cook.adapters.BackgroundImageAdapter
import com.sample.cook.databinding.FragmentSettingsBinding
import com.sample.cook.viewmodels.BackgroundImageViewModel

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

        return binding.root
    }
}