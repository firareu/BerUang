package com.capstone.beruang.ui.management

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.capstone.beruang.R
import com.capstone.beruang.databinding.FragmentManagementBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ManagementFragment : Fragment() {

    private var _binding: FragmentManagementBinding? = null
    private val binding get() = _binding!!
    private lateinit var managementViewModel: ManagementViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentManagementBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewPager: ViewPager2 = root.findViewById(R.id.view_pager)

        val tabs: TabLayout = root.findViewById(R.id.tabs)
        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity())
        viewPager.adapter = sectionsPagerAdapter
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()

        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        managementViewModel = ViewModelProvider(requireActivity()).get(ManagementViewModel::class.java)
        managementViewModel.loadingVisibility.observe(viewLifecycleOwner) { visibility ->
            // Sesuaikan visibilitas loadingFrame di ManagementFragment
            binding.loadingFrame.visibility = visibility
            binding.errorFrame.visibility = visibility
        }
        managementViewModel.errorVisibility.observe(viewLifecycleOwner) { visibility ->
            // Sesuaikan visibilitas loadingFrame di ManagementFragment
            binding.errorFrame.visibility = visibility
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.allocation,
            R.string.report
        )
    }
}