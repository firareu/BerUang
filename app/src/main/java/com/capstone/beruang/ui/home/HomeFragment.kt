package com.capstone.beruang.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.R
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.Result
import com.capstone.beruang.data.dataclass.ArticleData
import com.capstone.beruang.data.response.ListAllocationItem
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.FragmentHomeBinding
import com.capstone.beruang.ui.management.allocation.AllocationViewModel
import com.capstone.beruang.ui.management.allocation.AllocationViewModelFactory
import com.capstone.beruang.ui.management.allocation.detail.DetailAllocationAdapter
import com.capstone.beruang.ui.management.allocation.detail.DetailAllocationViewModel
import com.example.submission.data.retrofit.ApiConfig

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: DetailAllocationAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var apiService: ApiService

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        setMoneyData()
        setupAdapter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ApiService using ApiConfig
        apiService = ApiConfig.getApiService()

        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        // Initialize DetailAllocationAdapter
        homeAdapter = DetailAllocationAdapter()

        // Set up RecyclerView with LinearLayoutManager
        binding.rvAllocation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = homeAdapter

        // Observe outcomeData from ViewModel and update RecyclerView
        viewModel.outcomeData.observe(viewLifecycleOwner, { outcomeList ->
            homeAdapter.submitList(outcomeList)
        })

        // Set ApiService to ViewModel
        viewModel.apiService = apiService

        // Fetch outcome data after ApiService is initialized
        viewModel.fetchOutcomeData()
    }

    private fun setMoneyData() {
        val num: Int? = null // Misalnya: num = 1000

        /*//sisa keuangan
        val RestMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRestmoney.text = RestMoney

        //pengeluaran saat ini
        val SpendingMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvSpendingmoney.text = SpendingMoney*/

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
