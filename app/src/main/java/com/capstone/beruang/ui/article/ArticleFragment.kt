package com.capstone.beruang.ui.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.RowAdapter
import com.capstone.beruang.adapter.TagAdapter
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.data.TagData
import com.capstone.beruang.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root

        // Inside onCreateView or onCreate method in your fragment or activity
        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ArticleViewModel::class.java)
        setupAdapters()
        return view
    }

    private fun setupAdapters() {
        // Setup RowAdapter
        viewModel.articleList.observe(viewLifecycleOwner) { articles ->
            // Update the RecyclerView adapter with the new list of articles
            val adapter = RowAdapter(articles)
            binding.rvRow.adapter = adapter
            binding.rvRow.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
//        val dataArticle = ArticleData.articleList
//        val adapter = RowAdapter(dataArticle)
//        binding.rvRow.adapter = adapter
//        binding.rvRow.layoutManager =
//            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvRow.setHasFixedSize(true)

        // Setup TagAdapter
        val dataTag = TagData.tagList
        val tagAdapter = TagAdapter(requireContext(), dataTag)
        binding.rvTag.adapter = tagAdapter
        binding.rvTag.layoutManager = GridLayoutManager(requireContext(), 2)

        tagAdapter.setOnItemClickListener {
            val intent = Intent(requireContext(), ListArticleActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
