package com.capstone.beruang.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.beruang.R
import com.capstone.beruang.data.dataclass.Article
import com.capstone.beruang.ui.article.detail.DetailArticleActivity

class RowAdapter(private val articleList: List<Article>) :
    RecyclerView.Adapter<RowAdapter.ArticleViewHolder>() {

    class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.title_article_row)
        val imageView: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_article, parent, false)
        return ArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articleList[position]

        holder.titleTextView.text = currentArticle.title
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
