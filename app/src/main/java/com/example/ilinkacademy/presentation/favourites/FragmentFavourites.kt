package com.example.ilinkacademy.presentation.favourites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ilinkacademy.R
import com.example.ilinkacademy.databinding.FragmentFavouritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFavourites : Fragment(R.layout.fragment_favourites) {

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val favouritesViewModel by viewModels<FavouritesViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val manager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        val adapter = FavouritesAdapter {
            favouritesViewModel.deleteElement(it)
        }
        binding.rvSavedPictures.apply {
            this.adapter = adapter
            this.layoutManager = manager
        }
        favouritesViewModel.savedPiscLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        super.onViewCreated(view, savedInstanceState)
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}