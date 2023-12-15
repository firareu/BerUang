package com.capstone.beruang.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.appcompat.widget.Toolbar // Mengubah impor Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.R
import com.capstone.beruang.databinding.FragmentHomeBinding
import com.capstone.beruang.ui.login.LoginActivity
import com.capstone.beruang.ui.management.allocation.AllocationAdapter
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeAdapter: HomeAdapter = HomeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeAdapter.setFakeAllocations()

        binding.rvAllocation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = homeAdapter

        val imgItemPhoto = view?.findViewById<de.hdodenhof.circleimageview.CircleImageView>(R.id.img_item_photo)
        imgItemPhoto?.setImageResource(R.drawable.profile)

        /*val toolbar: Toolbar = requireActivity().findViewById(R.id.toolbar)
        toolbar.visibility = View.GONE*/

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        setMoneyData()
        return root
    }

    private fun setMoneyData() {
        val num: Int? = null // Misalnya: num = 1000

        //sisa keuangan
        val RestMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRestmoney.text = RestMoney

        //pengeluaran saat ini
        val SpendingMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvSpendingmoney.text = SpendingMoney

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
