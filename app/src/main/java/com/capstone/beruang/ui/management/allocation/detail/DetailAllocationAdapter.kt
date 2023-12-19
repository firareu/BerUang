package com.capstone.beruang.ui.management.allocation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.response.AllocationItem
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.ItemTypeAllocationBinding

class DetailAllocationAdapter:
    RecyclerView.Adapter<DetailAllocationAdapter.ViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val allocationList = ArrayList<AllocationItem>()

    fun submitList(allocations: List<AllocationItem>) {
        allocationList.clear()
        allocationList.addAll(allocations)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemTypeAllocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allocationList[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(allocationList[position])
        }
    }

    override fun getItemCount(): Int {
        return allocationList.size
    }

    inner class ViewHolder(private val binding: ItemTypeAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AllocationItem) {
            binding.tvAllocationtype.setText(item.category)
            binding.tvDate.setText(item.date)
            val totalText = itemView.context.getString(R.string.totalallocation, item.amount ?: "0")
            binding.tvRp.text = totalText
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: AllocationItem)
    }
}
