package com.suadahaji.postsapplication.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suadahaji.postsapplication.databinding.PostItemBinding
import com.suadahaji.postsapplication.model.Post

class PostListAdapter(private val posts: List<Post>) :
    RecyclerView.Adapter<PostListAdapter.PostViewHolder>() {

    inner class PostViewHolder(private val binding: PostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(postResponse: Post) {
            binding.post = postResponse
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = PostItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }
}