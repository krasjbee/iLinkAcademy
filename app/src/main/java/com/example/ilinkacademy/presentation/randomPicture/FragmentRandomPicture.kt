package com.example.ilinkacademy.presentation.randomPicture

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ilinkacademy.R
import com.example.ilinkacademy.databinding.FragmentRandomPictureBinding
import com.example.ilinkacademy.presentation.MainActivityViewModel
import com.example.ilinkacademy.utils.PictureState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentRandomPicture : Fragment(R.layout.fragment_random_picture) {

    private val viewModel by viewModels<MainActivityViewModel>()
    private var _binding: FragmentRandomPictureBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {


        viewModel.pictureLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PictureState.Initial -> {
                    Log.d("qwe", "onViewCreated: Initial ${savedInstanceState == null}")
                }
                is PictureState.Loading -> {
                    Log.d("qwe", "onViewCreated: Loading ")
                }
                is PictureState.Success -> {
                    Log.d("qwe", "onViewCreated: Success ")
                    binding.ivRandomPic.isGone = false
                    binding.ivRandomPic.isVisible = true
//                    binding.ivRandomPic.load(state.drawable)
                    binding.ivRandomPic.setImageDrawable(state.drawable)
                }
                is PictureState.Error -> {
                    Log.d("qwe", "onViewCreated: Error ")
                }
            }
        }

        binding.btRandomCat.setOnClickListener { viewModel.getRandomAnimal(MainActivityViewModel.Companion.Animal.CAT) }
        binding.btRandomDuck.setOnClickListener { viewModel.getRandomAnimal(MainActivityViewModel.Companion.Animal.DUCK) }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}