package com.example.retrofithilt

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofithilt.data.models.CommentResponse
import com.example.retrofithilt.databinding.ListItemBinding

class CommentAdapter(private val comments: List<CommentResponse>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    inner class CommentViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        return CommentViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = comments[position]

        holder.binding.apply {
            tvTitle.text = comment.name
            tvAlbumId.text = comment.body
        }

    }

    override fun getItemCount() = comments.size

}