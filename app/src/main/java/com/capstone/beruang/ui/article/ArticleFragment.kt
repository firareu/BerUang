package com.capstone.beruang.ui.article

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
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

class ArticleFragment: Fragment() {

    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticleBinding.inflate(inflater, container, false)
        val view = binding.root

        val dataArticle = ArticleData.articleList
        val recyclerView = binding.rvRow
        val adapter = RowAdapter(dataArticle)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)

        val dataTag = TagData.tagList
        val tagAdapter = TagAdapter(requireContext(), dataTag)
        val rvForTag = binding.rvTag
        rvForTag.adapter = tagAdapter
        rvForTag.layoutManager = GridLayoutManager(requireContext(), 2)

        tagAdapter.setOnItemClickListener {
            val intent = Intent(requireContext(), ListArticleActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
