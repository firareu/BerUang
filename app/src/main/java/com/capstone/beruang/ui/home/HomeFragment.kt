package com.capstone.beruang.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.R
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.dataclass.ArticleData
import com.capstone.beruang.databinding.FragmentHomeBinding

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

//        homeAdapter.setFakeAllocations()

        binding.rvAllocation.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = homeAdapter

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        setMoneyData()
        setupAdapter()
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

    private fun setupAdapter() {
        val dataArticle = ArticleData.articleList.take(5)
        val recyclerView = binding.rvArticlelist
        val adapter = ArticleListAdapter(dataArticle)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
