package com.capstone.beruang.ui.management

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.R
import com.capstone.beruang.databinding.FragmentManagementBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding!!
    lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val managementViewModel =
            ViewModelProvider(this).get(ManagementViewModel::class.java)

        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*val textView: TextView = binding.textManagement
        managementViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }*/

        pieChart = root.findViewById(R.id.pie_chart)


        val list:ArrayList<PieEntry> = ArrayList()

        list.add(PieEntry(100f,"Needs"))
        list.add(PieEntry(101f,"Lifestyle"))
        list.add(PieEntry(102f,"Goals"))
        list.add(PieEntry(103f,"Tabungan"))
        list.add(PieEntry(104f,"Utang"))

        val pieDataSet = PieDataSet(list, "")
        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS, 255)
        pieDataSet.valueTextColor = ContextCompat.getColor(requireContext(), R.color.black_200)
        pieDataSet.valueTextSize = 12f

        val pieData= PieData(pieDataSet)

        pieChart.data= pieData

        pieChart.description.text= ""

        pieChart.centerText= resources.getString(R.string.allocation)

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}