package com.capstone.beruang.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.beruang.data.response.article.NewsResponseItem
import com.capstone.beruang.databinding.RowArticleBinding
import com.capstone.beruang.ui.article.detail.DetailArticleActivity

class RowAdapter(private val articleList: List<NewsResponseItem>) :
    RecyclerView.Adapter<RowAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(binding: RowArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleTextView = binding.titleArticleRow
        val imageView = binding.imgItemPhoto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = RowArticleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articleList[position]

        holder.titleTextView.text = currentArticle.headline
        Glide.with(holder.itemView.context)
            .load(currentArticle.image)
            .into(holder.imageView)
        holder.itemView.setOnClickListener {
            val intent = Intent(it.context, DetailArticleActivity::class.java)
            intent.putExtra(DetailArticleActivity.ARTICLE_KEY, currentArticle)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}