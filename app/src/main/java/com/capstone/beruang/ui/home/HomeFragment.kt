package com.capstone.beruang.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.dataclass.ArticleData
import com.capstone.beruang.data.dataclass.ArticleRepository
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.response.LoginResponse
import com.capstone.beruang.data.retrofit.ApiConfig
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.FragmentHomeBinding
import com.capstone.beruang.ui.article.ArticleViewModel
import com.capstone.beruang.ui.article.ArticleViewModelFactory
import com.capstone.beruang.ui.management.allocation.detail.DetailAllocationAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: DetailAllocationAdapter
    private lateinit var viewModel: HomeViewModel
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var apiService: ApiService
    private val userRepository: UserRepository by lazy {
        UserRepository(PreferenceManager.getInstance(requireContext()))
    }
    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        (activity as AppCompatActivity?)?.supportActionBar?.hide()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val loginResponse: LoginResponse? = userRepository.loginUser(email, password)
        // Initialize ApiService using ApiConfig
        apiService = ApiConfig.getApiService()
        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(repository)
        articleViewModel = ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]

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
        setupAdapter()
    }

    private fun setupAdapter() {
        articleViewModel.articleList.observe(viewLifecycleOwner) { articles ->
            // Update the RecyclerView adapter with the new list of articles
            val dataArticle = articles.take(5)
            val adapter = ArticleListAdapter(dataArticle)
            binding.rvArticlelist.adapter = adapter
            binding.rvArticlelist.layoutManager =
                LinearLayoutManager(requireContext())
        }
//        recyclerView.setHasFixedSize(true)
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

