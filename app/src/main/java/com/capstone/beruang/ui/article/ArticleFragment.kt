package com.capstone.beruang.ui.article

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.RowAdapter
import com.capstone.beruang.adapter.TagAdapter
import com.capstone.beruang.data.ArticleData
import com.capstone.beruang.data.TagData
import com.capstone.beruang.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root
        setupAdapters()
        return view
    }

    private fun setupAdapters() {
        // Setup RowAdapter
        val dataArticle = ArticleData.articleList
        val adapter = RowAdapter(dataArticle)
        binding.rvRow.adapter = adapter
        binding.rvRow.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
