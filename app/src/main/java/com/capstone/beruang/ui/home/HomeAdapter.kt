package com.capstone.beruang.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.data.fakedata.FakeDataGenerator
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.databinding.ItemHomeAllocationBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val allocationList = ArrayList<ListAllocationItem>()

    fun setFakeAllocations() {
        val fakeAllocations = FakeDataGenerator.generateFakeAllocations()
        allocationList.clear()
        allocationList.addAll(fakeAllocations)
        notifyDataSetChanged()
    }

    fun submitList(allocations: List<ListAllocationItem>) {
        allocationList.clear()
        allocationList.addAll(allocations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeAllocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allocationList[position])
        holder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(allocationList[position]) }
    }

    override fun getItemCount(): Int {
        return allocationList.size
    }

    inner class ViewHolder(private val binding: ItemHomeAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(allocation: ListAllocationItem) {
            val formattedPercent = String.format("%d %%", allocation.percent?.toInt() ?: 0)
            // Set teks pada TextView
            binding.tvPercent.text = formattedPercent
            binding.tvAllocationtype.text = allocation.allocation_name
            // Set other views if needed based on ListAllocationItem data
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: ListAllocationItem)
    }
}