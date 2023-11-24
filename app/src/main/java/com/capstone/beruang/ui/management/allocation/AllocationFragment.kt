package com.capstone.beruang.ui.management.allocation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.R
import com.capstone.beruang.data.Allocation
import com.capstone.beruang.databinding.FragmentAllocationBinding
import com.capstone.beruang.ui.management.edit.EditActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class AllocationFragment : Fragment() {

    private var _binding: FragmentAllocationBinding? = null
    private val binding get() = _binding!!
    lateinit var pieChart: PieChart
    private lateinit var viewModel: AllocationViewModel
    private val allocationAdapter: AllocationAdapter = AllocationAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllocationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAction()
        pieChart = root.findViewById(R.id.pie_chart)
        pieChart.setUsePercentValues(true)
        pieChart.setDrawEntryLabels(false)
        binding.rvAllocation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = allocationAdapter

        viewModel = ViewModelProvider(this).get(AllocationViewModel::class.java)

        // Panggil fungsi untuk mengambil data dari database saat fragment dibuat
        viewModel.loadAllocationsFromDatabase(requireContext())

        // Observasi perubahan data dan update UI ketika data berubah
        viewModel.allocations.observe(viewLifecycleOwner, Observer { allocations ->
            allocations?.let {
                setFragmentData(ArrayList(allocations))
            }
        })

        setMoneyData()
        return root
    }

    private fun setupAction() {
        binding.buttonEdit.setOnClickListener {
            val intentEdit = Intent(requireContext(), EditActivity::class.java)
            startActivity(intentEdit)
        }
    }

    private fun setMoneyData() {
        val num: Int? = null // Misalnya: num = 1000

        //pemasukkan
        val TVMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvMoney.text = TVMoney

        //sisa keuangan
        val RestMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRestmoney.text = RestMoney

        //pengeluaran saat ini
        val SpendingMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvSpendingmoney.text = SpendingMoney

    }

    fun setFragmentData(allocations: ArrayList<Allocation>) {
        piechart(allocations)
        setMoneyData()
        viewModel.allocations.observe(viewLifecycleOwner) { allocation ->
            if (allocation != null) {
                allocationAdapter.submitList(allocations)
                allocationAdapter.notifyDataSetChanged()

            }
        }
    }

    private fun piechart(allocations: ArrayList<Allocation>) {
        val list: ArrayList<PieEntry> = ArrayList()

        for (allocation in allocations) {
            allocation.percent?.let {
                list.add(PieEntry(it, allocation.allocation_name))
            }
        }

        val pieDataSet = PieDataSet(list, "")
        pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS, 255)
        pieDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.black_200)
        pieDataSet.valueTextSize = 12f

        val pieData = PieData(pieDataSet)
        pieData.setValueFormatter(PercentFormatter(pieChart)) // Mengatur formatter sebagai PercentFormatter

        pieChart.data = pieData

        pieChart.description.text = ""

        pieChart.centerText = resources.getString(R.string.allocation)

        pieChart.animateY(2000)

        // Set up legend
        val legend: Legend = pieChart.legend
        legend.isEnabled = true
        pieChart.legend.verticalAlignment = Legend.LegendVerticalAlignment.CENTER
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL
        legend.setDrawInside(false)
        legend.xEntrySpace = 8f
        legend.yEntrySpace = 0f
        legend.yOffset = 0f
        legend.textSize = 12f
        legend.formSize = 10f

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"

    }
}