package com.capstone.beruang.ui.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.databinding.ActivityListArticleBinding

class ListArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListArticleBinding
    private lateinit var viewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = ArticleRepository()
        val viewModelFactory = ArticleViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ArticleViewModel::class.java]
        setupAdapter()

//        val dataArticle = ArticleData.articleList
//        val recyclerView = binding.rvArticle
//        val adapter = ArticleListAdapter(dataArticle)
//
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
        setupAction()
    }

    private fun setupAdapter() {
        viewModel.articleList.observe(this) { articles ->
            // Update the RecyclerView adapter with the new list of articles
            val adapter = ArticleListAdapter()
            binding.rvArticle.adapter = adapter
            binding.rvArticle.layoutManager =
                LinearLayoutManager(this)
        }
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}