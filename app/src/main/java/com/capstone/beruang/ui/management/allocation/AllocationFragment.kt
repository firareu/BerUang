package com.capstone.beruang.ui.management.allocation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.R
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.databinding.FragmentAllocationBinding
import com.capstone.beruang.ui.management.allocation.detail.DetailAllocationActivity
import com.capstone.beruang.ui.management.allocation.edit.EditActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.capstone.beruang.data.Result
import com.example.submission.data.retrofit.ApiConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

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
        setUserData()
        setMoneyData()
        return root
    }

    private fun setSalary() {
        lifecycleScope.launch {
            try {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1
                val currentDateString = "$year-$month"

                viewModel.getSalaryFromApi(currentDateString)

                viewModel.salary.observe(viewLifecycleOwner) { salary ->
                    salary?.let {
                        Log.e("salary1", salary.toString())

                        val TVMoney = getString(R.string.rupiah, salary.toInt())
                        Log.e("salary2", TVMoney)

                        binding.tvMoney.text = TVMoney
                    }
                }
            } catch (e: Exception) {
                Log.e("salary", "Error getting salary: ${e.message}")
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), AllocationViewModelFactory.getInstance(requireActivity()))[AllocationViewModel::class.java]
        Log.d("hahhh", viewModel.toString())

        viewModel.allocations().observe(requireActivity()){ allocationResponse ->
            Log.d("check", allocationResponse.toString())
            when (allocationResponse) {
                is Result.Loading -> {
//                    showLoading(true)
                    Log.d("loading", allocationResponse.toString())
                }
                is Result.Success -> {
                    Log.d("sukses1", allocationResponse.toString())
                    allocationResponse.data?.let { response ->
                        Log.d("sukses2", response.toString())
                        val allocations = response.allocation
                        Log.d("hmmm", allocations.toString())
                        if (allocations?.isEmpty()!!) {
                            allocationAdapter.submitList(emptyList())
                            Log.d("coba fakedatae1", allocations.toString())
                        } else {
                            setFragmentData(allocations as ArrayList<ListAllocationItem>)
                            setSalary()
                            Log.d("coba fakedatae2", allocations.toString())
                        }
                    }
                }
                is Result.Error -> {
                    Log.d("error", allocationResponse.toString())
//                    showLoading(false)
                }
            }

        }

        val apiService = ApiConfig.getApiService()
        viewModel.apiService = apiService
    }

    private fun setUserData() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvAllocation.layoutManager = layoutManager
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = allocationAdapter
        allocationAdapter.setOnItemClickCallback(object : AllocationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ListAllocationItem) {
                val intent = Intent(requireContext(), DetailAllocationActivity::class.java)
                intent.putExtra(DetailAllocationActivity.KEY_CONTENT, data)
                startActivity(intent)
            }
        })
    }

    private fun setupAction() {
        binding.buttonEdit.setOnClickListener {
            val intentEdit = Intent(requireContext(), EditActivity::class.java)
            startActivity(intentEdit)
        }
    }

    private fun setMoneyData() {
        val num: Int? = null

        //pemasukkan
        val TVMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvMoney.text = TVMoney

        //sisa keuangan
        /*val RestMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRestmoney.text = RestMoney

        //pengeluaran saat ini
        val SpendingMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvSpendingmoney.text = SpendingMoney*/

    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFragmentData(allocations: ArrayList<ListAllocationItem>) {
        piechart(allocations)
        setMoneyData()
        viewModel.allocations().observe(viewLifecycleOwner) { allocation ->
            if (allocation != null) {
                allocationAdapter.submitList(allocations)
                allocationAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun piechart(allocations: ArrayList<ListAllocationItem>) {
        val list: ArrayList<PieEntry> = ArrayList()

        for (allocation in allocations) {
            allocation.percentage?.let {
                list.add(PieEntry(it, allocation.category))
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



/*
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
import com.capstone.beruang.data.dataclass.Allocation
import com.capstone.beruang.databinding.FragmentAllocationBinding
import com.capstone.beruang.ui.management.allocation.edit.EditActivity
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
}*/
