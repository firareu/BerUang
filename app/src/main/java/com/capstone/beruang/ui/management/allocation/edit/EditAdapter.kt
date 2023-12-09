package com.capstone.beruang.ui.management.allocation.edit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.capstone.beruang.R
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.data.DatabaseHelper
import com.capstone.beruang.databinding.ItemAllocationBinding
import com.capstone.beruang.databinding.ItemCategoryAllocationBinding
import com.capstone.beruang.ui.management.allocation.AllocationAdapter

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

    fun updateSalary(newSalary: Float) {
        salary = newSalary
        notifyDataSetChanged()
    }

    fun updateData(){
        notifyDataSetChanged()
    }

    fun itemRemovedAtUpdateList(position: Int){
        notifyItemRemoved(position)
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

    fun removeItem(position: Int) {
        val allocationToRemove = allocationList[position]
        val removedId = allocationToRemove.id ?: return // Ambil ID dari item yang akan dihapus

        // Hapus item dari database
        databaseHelper.deleteAllocation(removedId)

        // Hapus item dari daftar
        allocationList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class ViewHolder(private val binding: ItemCategoryAllocationBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnCross.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val deletedItem = allocationList[position]
                    allocationList.removeAt(position)
                    notifyItemRemoved(position)

                    // Hapus item dari database berdasarkan ID
                    databaseHelper.deleteAllocation(deletedItem.id)
                }
            }
        }
        fun bind(item: Allocation) {
            binding.edtNameallocation.setText(item.allocation_name)
            binding.edtPercent.setText(item.percent?.toString() ?: "")

            // Tampilkan nilai total secara live di tv_total
            val totalText = itemView.context.getString(R.string.totalallocation, item.total ?: "0")
            binding.tvTotal.text = totalText
        }
    }


    interface OnItemClickCallback {
        fun onItemClicked(data: Allocation)
    }
}
