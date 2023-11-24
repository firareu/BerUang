package com.capstone.beruang.ui.management.allocation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.databinding.ItemAllocationBinding

class AllocationAdapter : RecyclerView.Adapter<AllocationAdapter.ViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val allocationList = ArrayList<Allocation>()

    fun submitList(allocations: ArrayList<Allocation>) {
        allocationList.clear()
        allocationList.addAll(allocations)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemAllocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allocationList[position])
        holder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(allocationList[position]) }
    }

    override fun getItemCount(): Int {
        return allocationList.size
    }

    inner class ViewHolder(private val binding: ItemAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(allocation: Allocation) {
            val formattedPercent = String.format("%d %%", allocation.percent?.toInt() ?: 0)
            // Set teks pada TextView
            binding.tvPercent.text = formattedPercent
            binding.tvAllocationtype.text = allocation.allocation_name
            binding.tvRp.text = allocation.total.toString()
            // Set other views if needed based on Allocation data
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Allocation)
    }
}
