package com.capstone.beruang.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.beruang.R
import com.capstone.beruang.data.dataclass.Tag
import com.capstone.beruang.data.response.TagResponse
import com.capstone.beruang.databinding.TagCardBinding


class TagAdapter(private val context: Context, private val tagList: List<TagResponse>)
    : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    private var onItemClickListener: ((TagResponse) -> Unit)? = null

    fun setOnItemClickListener(listener: (TagResponse) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = TagCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentTag = tagList[position]
        holder.bind(currentTag)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentTag)
        }
    }

    override fun getItemCount(): Int {
        return tagList.size
    }

    inner class TagViewHolder(private val binding: TagCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tag: TagResponse) {
            binding.tagTitle.text = tag.title

            Glide.with(context)
                .load(tag.image)
                .into(binding.imgItemPhoto)
        }
    }
}
/*
class TagAdapter(private val context: Context, private var tagList: List<Tag>) :
    RecyclerView.Adapter<TagAdapter.TagViewHolder>() {

    // Change the parameter type to List<Tag>
    fun setData(newTagList: List<Tag>) {
        tagList = newTagList
        notifyDataSetChanged()
    }
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
        // Assuming that 'image' is an attribute in your Tag class
        holder.imageView.setImageResource(currentTag.image)

        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentTag)
        }
    }

    override fun getItemCount(): Int {
        return tagList.size
    }
}*/
