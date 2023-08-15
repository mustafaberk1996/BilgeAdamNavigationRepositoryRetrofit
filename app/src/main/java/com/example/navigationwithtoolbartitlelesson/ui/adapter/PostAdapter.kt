package com.example.navigationwithtoolbartitlelesson.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationwithtoolbartitlelesson.data.api.model.Post
import com.example.navigationwithtoolbartitlelesson.databinding.PostListItemBinding

class PostsAdapter(
    private val context: Context,
    private val posts: List<Post>,
    val onClick: (post: Post) -> Unit
) : RecyclerView.Adapter<PostsAdapter.CustomViewHolder>() {

    class CustomViewHolder(binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
        val tvBody = binding.tvBody
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = PostListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        val posts = posts[position]
        holder.tvTitle.text = posts.title
        holder.tvBody.text = posts.body

        holder.itemView.setOnClickListener {
            onClick(posts)
        }
    }
}