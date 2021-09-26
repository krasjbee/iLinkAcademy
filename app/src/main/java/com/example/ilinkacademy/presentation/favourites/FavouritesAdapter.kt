package com.example.ilinkacademy.presentation.favourites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ilinkacademy.data.local.dto.AnimalPic
import com.example.ilinkacademy.databinding.FavViewHolderBinding


class FavouritesAdapter(private val onClick: (AnimalPic) -> Unit) :
    ListAdapter<AnimalPic, FavouritesAdapter.FavViewHolder>(diffUtilCallback) {

    inner class FavViewHolder(private val binding: FavViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AnimalPic) {
            Glide.with(itemView).load(item.imageUri).into(binding.ivFavPic)
            binding.tbLikeButton.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FavViewHolderBinding.inflate(inflater, parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    private object diffUtilCallback : DiffUtil.ItemCallback<AnimalPic>() {
        override fun areItemsTheSame(oldItem: AnimalPic, newItem: AnimalPic): Boolean =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: AnimalPic, newItem: AnimalPic): Boolean =
            oldItem.imageUri == newItem.imageUri
    }
}
