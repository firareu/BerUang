package com.capstone.beruang.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.Tag

class TagAdapter(private val articleList: List<Tag>) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    class TagViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.tag_title)
        val imageView: ImageView = itemView.findViewById(R.id.img_item_photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tag_card, parent, false)
        return TagViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentArticle = articleList[position]

        holder.titleTextView.text = currentArticle.title
        holder.imageView.setImageResource(currentArticle.image)
    }

    override fun getItemCount(): Int {
        return articleList.size
    }
}
