package com.capstone.beruang.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.beruang.data.Article
import com.capstone.beruang.databinding.ArticleViewBinding

class ArticleListAdapter(private val articleList: List<Article>) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(binding: ArticleViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleTextView = binding.titleArticle
        val imageView = binding.imgItemPhoto
        val content = binding.contentHint
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ArticleViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articleList[position]

        holder.titleTextView.text = currentArticle.title
        Glide.with(holder.itemView.context)
            .load(currentArticle.image)
            .into(holder.imageView)
        holder.content.text = currentArticle.content

    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}

