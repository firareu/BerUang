package com.capstone.beruang.ui.welcome

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPageAdapter(Fa:FragmentActivity,listener:(CharSequence)->Unit):FragmentStateAdapter(Fa) {
    private val dataFragments= mutableListOf(
        FirstSlideFragment.newInstance("0"),
        FirstSlideFragment.newInstance("1"),
        FirstSlideFragment.newInstance("2"),
    )
    override fun getItemCount(): Int =4

    override fun createFragment(position: Int): Fragment =dataFragments[position]
}