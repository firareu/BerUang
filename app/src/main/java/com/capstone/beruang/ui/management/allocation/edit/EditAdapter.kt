package com.capstone.beruang.ui.management.allocation.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.data.DatabaseHelper
import com.capstone.beruang.databinding.ItemCategoryAllocationBinding

class EditAdapter(private val databaseHelper: DatabaseHelper) : RecyclerView.Adapter<EditAdapter.ViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val allocationList = ArrayList<Allocation>()
    private lateinit var editViewModel: EditViewModel
    private var salary: Float = 0f

    fun submitList(allocations: ArrayList<Allocation>) {
        allocationList.clear()
        allocationList.addAll(allocations)
        notifyDataSetChanged()
    }

    fun addAllocation(allocation: Allocation) {
        allocationList.add(allocation)
        notifyItemInserted(allocationList.size - 1)
    }


    fun updateSalary(newSalary: Float) {
        salary = newSalary
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryAllocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allocationList[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(allocationList[position])
            // Calculate allocation based on salary when item clicked
            val percent = allocationList[position].percent ?: 0f
            editViewModel.calculateAllocations(salary, percent)
        }
    }

    override fun getItemCount(): Int {
        return allocationList.size
    }

    inner class ViewHolder(private val binding: ItemCategoryAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.btnCross.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    if (allocationList.isNotEmpty()) {
                        val deletedItem = allocationList[position]
                        allocationList.removeAt(position)
                        notifyItemRemoved(position)

                        databaseHelper.deleteAllocation(deletedItem.id)
                    } else {
                        allocationList.removeAt(allocationList.size - 1)
                        notifyItemRemoved(allocationList.size)
                    }
                }
            }
        }

        fun bind(item: Allocation) {
            binding.edtNameallocation.setText(item.allocation_name)
            binding.edtPercent.setText(item.percent?.toString() ?: "")

            val totalText = itemView.context.getString(R.string.totalallocation, item.total ?: "0")
            binding.tvTotal.text = totalText
        }

    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Allocation)
    }
}
