package com.capstone.beruang.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.RowAdapter
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

        val articleList = listOf(
            "Fakta Koceng yang Mengira Dirinya Kura-Kura",
            "Article 2",
            "Article 3"
        )

        val recyclerView = binding.rvRow
        val adapter = RowAdapter(articleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
