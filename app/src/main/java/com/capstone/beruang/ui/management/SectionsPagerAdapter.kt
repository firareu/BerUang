package com.capstone.beruang.ui.management

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.capstone.beruang.ui.management.allocation.AllocationFragment
import com.capstone.beruang.ui.management.report.ReportFragment

class SectionsPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val fragment = AllocationFragment()
                fragment.arguments = Bundle().apply {
                    putInt(AllocationFragment.ARG_SECTION_NUMBER, position + 1)
                }
                fragment
            }
            1 -> {
                val fragment = ReportFragment()
                fragment.arguments = Bundle().apply {
                    putInt(ReportFragment.ARG_SECTION_NUMBER, position + 1)
                }
                fragment
            }
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}