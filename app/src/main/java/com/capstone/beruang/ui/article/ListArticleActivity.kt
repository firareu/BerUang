package com.capstone.beruang.ui.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.ArticleData
import com.capstone.beruang.databinding.ActivityListArticleBinding

class ListArticleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityListArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataArticle = ArticleData.articleList
        val recyclerView = binding.rvArticle
        val adapter = ArticleListAdapter(dataArticle)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        setupAction()
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}