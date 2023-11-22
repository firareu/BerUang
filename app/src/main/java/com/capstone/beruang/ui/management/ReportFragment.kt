package com.capstone.beruang.ui.management

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.capstone.beruang.databinding.FragmentReportBinding
import com.capstone.beruang.ui.home.HomeViewModel

class ReportFragment : Fragment() {
    private var _binding: FragmentReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

//    lateinit var goBarChart: Button

//    lateinit var goPieChart: Button

//    lateinit var goRadarChart: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*binding.goPieChart.setOnClickListener {
            startActivity(Intent(requireContext(), PieChartActivity::class.java))
        }*/

        val homeViewModel =
            ViewModelProvider(this).get(ManagementViewModel::class.java)

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_SECTION_NUMBER = "section_number"
    }
}