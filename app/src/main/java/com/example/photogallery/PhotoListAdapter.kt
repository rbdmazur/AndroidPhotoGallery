package com.example.photogallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.photogallery.databinding.PhotoItemBinding
import com.example.photogallery.models.Photo

class PhotoViewHolder(
    private val binding: PhotoItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(photo: Photo) {
        binding.itemView.load(photo.src.medium)
    }
}
class PhotoListAdapter(
    private val photoList: List<Photo>
) : RecyclerView.Adapter<PhotoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PhotoItemBinding.inflate(inflater, parent, false)
        return PhotoViewHolder(binding)
    }

    override fun getItemCount(): Int = photoList.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photoList[position]
        holder.bind(photo)
    }

}