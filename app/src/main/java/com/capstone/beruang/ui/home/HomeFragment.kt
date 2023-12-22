package com.capstone.beruang.ui.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.beruang.R
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.data.repository.PreferenceManager
import com.capstone.beruang.data.repository.UserRepository
import com.capstone.beruang.data.retrofit.ApiConfig
import com.capstone.beruang.data.retrofit.ApiService
import com.capstone.beruang.databinding.FragmentHomeBinding
import com.capstone.beruang.ui.article.ArticleViewModel
import com.capstone.beruang.ui.article.ArticleViewModelFactory
import com.capstone.beruang.ui.management.allocation.add.AddOutcomeActivity
import com.capstone.beruang.ui.management.allocation.detail.DetailAllocationAdapter
import com.capstone.beruang.ui.management.allocation.edit.EditActivity
import kotlinx.coroutines.launch

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
    private lateinit var userId: String
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
        userId = userRepository.getUserId().toString()
        Log.d("HomeFragment", userId)

        apiService = ApiConfig.getApiService()
        lifecycleScope.launch {
            try {
                val response = apiService.getUserData(userId)
                val user = response.user
                val tvUsername = view.findViewById<TextView>(R.id.tv_username)
                tvUsername.text = user.name
                context?.let {
                    Glide.with(it)
                        .load(user.profilePicture)
                        .into(binding.imgItemPhoto)
                }
            } catch (e: Exception) {
                Log.e("UserData", "Failed to fetch user data: ${e.message}")
            }
        }

        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(repository)
        articleViewModel = ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeAdapter = DetailAllocationAdapter()

        binding.rvAllocation.layoutManager = LinearLayoutManager(requireContext())
        binding.rvAllocation.setHasFixedSize(true)
        binding.rvAllocation.adapter = homeAdapter

        viewModel.outcomeData.observe(viewLifecycleOwner, { outcomeList ->
            homeAdapter.submitList(outcomeList)
        })

        viewModel.totalOutcomeData.observe(viewLifecycleOwner, { totalOutcome ->
            val TVMoney = getString(R.string.rupiah, totalOutcome ?: 0)
            binding.tvRpoutcomenow.text = TVMoney
        })
        viewModel.apiService = apiService

        viewModel.fetchOutcomeData(userId)
        viewModel.fetchTotalOutcomeData(userId)
        setupAdapter()
        setupAction()
        setMoneyData()
    }

    private fun setMoneyData() {
        val num: Int? = null
        //outcome
        val TVMoney = getString(R.string.rupiah, num ?: 0)
        binding.tvRpoutcomenow.text = TVMoney


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
    }

    private fun setupAction() {
        binding.tvAdd.setOnClickListener {
            val intentEdit = Intent(requireContext(), AddOutcomeActivity::class.java)
            startActivity(intentEdit)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
