package com.example.demoapp.imagelist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.databinding.LayoutItemImageListBinding
import com.example.demoapp.ui.ImageItemUiModel

class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ImageListViewHolder>() {

    private val imageList = mutableListOf<ImageItemUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = LayoutItemImageListBinding.inflate(layoutInflater, parent, false)

        return ImageListViewHolder(binding)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ImageListViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    fun updateImageList(images: List<ImageItemUiModel>) {
        val previousSize = imageList.size
        imageList.addAll(images)
        notifyItemRangeInserted(previousSize, images.size)
    }

    fun getImageList() = imageList

    inner class ImageListViewHolder(private val binding: LayoutItemImageListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(image: ImageItemUiModel) {
            with(binding) {
                this.image = image
                executePendingBindings()
            }

        }
    }
}