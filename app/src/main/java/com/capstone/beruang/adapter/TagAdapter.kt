package com.capstone.beruang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.data.Tag
import com.capstone.beruang.databinding.TagCardBinding

class TagAdapter(private val context: Context, private val tagList: List<Tag>) :
    RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    class TagViewHolder(private val binding: TagCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleTextView = binding.tagTitle
        val imageView = binding.imgItemPhoto
    }

    private var onItemClickListener: ((Tag) -> Unit)? = null

    fun setOnItemClickListener(listener: (Tag) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding =
            TagCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentTag = tagList[position]
        holder.titleTextView.text = currentTag.title
        holder.imageView.setImageResource(currentTag.image)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentTag)
        }
    }

    override fun getItemCount(): Int {
        return tagList.size
    }
}