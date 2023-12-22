package com.capstone.beruang.ui.article.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.capstone.beruang.R
import com.capstone.beruang.adapter.ArticleListAdapter
import com.capstone.beruang.data.ArticleData
import com.capstone.beruang.data.ArticleRepository
import com.capstone.beruang.data.response.article.NewsResponseItem
import com.capstone.beruang.databinding.ActivityDetailArticleBinding
import com.capstone.beruang.ui.article.ArticleViewModel
import com.capstone.beruang.ui.article.ArticleViewModelFactory

class DetailArticleActivity : AppCompatActivity() {

    companion object {
        const val ARTICLE_KEY = "article"
    }

    private lateinit var binding: ActivityDetailArticleBinding
    private lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository = ArticleRepository()
        viewModel = ViewModelProvider(this,
            ArticleViewModelFactory(repository))[ArticleViewModel::class.java]


        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailArticle()
        setupAdapter()
    }

    @Suppress("DEPRECATION")
    private fun detailArticle() {
        val article = intent.getParcelableExtra<NewsResponseItem>(ARTICLE_KEY)

        if (article != null) {
            binding.articleTitle.text = article.headline
            binding.authorLabel.text = article.author
            binding.dateArticle.text = article.date
            Glide.with(this)
                .load(article.image)
                .into(binding.imageArticle)

            binding.articleContent.text = article.content

            updateBookmarkButtonState(article.isBookmarked)
        }
    }
    private fun setupAdapter() {
        val dataArticle = ArticleData.articleList.take(5)
        viewModel.articleList.observe(this) { articles ->
            // Update the RecyclerView adapter with the new list of articles
            val adapter = ArticleListAdapter(articles)
            binding.rvRecommend.adapter = adapter
            binding.rvRecommend.layoutManager =
                LinearLayoutManager(this)
        }
//        recyclerView.setHasFixedSize(true)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }

    private fun updateBookmarkButtonState(isBookmarked: Boolean) {
        val bookmarkIcon =
            if (isBookmarked) R.drawable.ic_bookmarksarticle else R.drawable.ic_bookmark_border
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Go back when the Up button is clicked
                true
            }

            R.id.btn_bookmark -> {
                val article = intent.getParcelableExtra<NewsResponseItem>(ARTICLE_KEY)
                article?.let {
                    it.isBookmarked = !it.isBookmarked
                    updateBookmarkButtonState(it.isBookmarked, item)

                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun updateBookmarkButtonState(isBookmarked: Boolean, menuItem: MenuItem) {
        val bookmarkIcon = if (isBookmarked) R.drawable.ic_bookmarksarticle else R.drawable.ic_bookmark_border
        menuItem.setIcon(bookmarkIcon)
    }


}