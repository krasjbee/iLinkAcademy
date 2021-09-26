package com.example.ilinkacademy.presentation.randomPicture

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.ilinkacademy.R
import com.example.ilinkacademy.databinding.FragmentRandomPictureBinding
import com.example.ilinkacademy.utils.PictureState
import com.example.ilinkacademy.utils.toByteArray
import dagger.hilt.android.AndroidEntryPoint
import java.io.File

@AndroidEntryPoint
class FragmentRandomPicture : Fragment(R.layout.fragment_random_picture) {

    private val viewModel by viewModels<RandomPictureViewModel>()
    private var _binding: FragmentRandomPictureBinding? = null
    private val binding get() = requireNotNull(_binding)

    /**
     * Shows in toast also shows error indicator
     */
    private fun showError(errorMessage: String) {
        binding.piLoading.isVisible = false
        binding.ivRandomPic.isVisible = true
        binding.tbLikeButton.isVisible = false
        binding.ivRandomPic.setImageResource(R.drawable.ic_baseline_cancel_24)
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    /**
     * Shows load indication
     */
    private fun showLoad() {
        binding.piLoading.isVisible = true
        binding.ivRandomPic.isVisible = false
        binding.tbLikeButton.isVisible = false
    }

    /**
     * Shows picture in case of successful request
     */
    private fun showPicture() {
        binding.piLoading.isVisible = false
        binding.ivRandomPic.isVisible = true
        binding.tbLikeButton.isVisible = true
    }

    /**
     * Shows initial state of current screen
     */
    private fun showInitialState() {
        binding.piLoading.isVisible = false
        binding.ivRandomPic.isVisible = false
        binding.tbLikeButton.isVisible = false
    }

    /**
     * Saves file named as end of url to local folder
     */
    private fun saveToFile(drawable: Drawable, url: String): String {
        val filename = url.substringAfterLast("/")
        val file = File(context?.filesDir, filename)
        context?.openFileOutput(filename, Context.MODE_PRIVATE).use { stream ->
            stream?.write(drawable.toByteArray())
        }
        return Uri.fromFile(file).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRandomPictureBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var drawable: Drawable? = null
        var url: String? = null
        viewModel.pictureLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is PictureState.Initial -> {
                    showInitialState()
                }
                is PictureState.Loading -> {
                    showLoad()
                }
                is PictureState.Success -> {
                    Log.d("qwe", "onViewCreated: Success ")
                    showPicture()
                    Glide.with(this).load(state.drawable).into(binding.ivRandomPic)
                    drawable = state.drawable
                    url = state.url.also { Log.d("url", "onViewCreated:$it ") }
                }
                is PictureState.Error -> {
                    showError(state.error)
                }
            }
        }

        binding.btRandomCat.setOnClickListener { viewModel.getRandomAnimal(RandomPictureViewModel.Companion.Animal.CAT) }
        binding.btRandomDuck.setOnClickListener { viewModel.getRandomAnimal(RandomPictureViewModel.Companion.Animal.DUCK) }
        binding.tbLikeButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                val uri = saveToFile(drawable!!, url!!)
                viewModel.saveToFavourites(uri, url!!.also { Log.d("file2", "onViewCreated:$it ") })
            }

        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}