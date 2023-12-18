package com.capstone.beruang.ui.article.detail

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.beruang.R
import com.capstone.beruang.data.response.article.ArticlesItem
import com.capstone.beruang.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {

    companion object {
        const val ARTICLE_KEY = "article"
    }

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailArticle()
    }

    @Suppress("DEPRECATION")
    private fun detailArticle() {
        val article = intent.getParcelableExtra<ArticlesItem>(ARTICLE_KEY)

        if (article != null) {
            binding.articleTitle.text = article.title
            Glide.with(this)
                .load(article.urlToImage)
                .into(binding.imageArticle)

            binding.articleContent.text = article.content
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.article_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // Go back when the Up button is clicked
                true
            }

            R.id.btn_bookmark -> {
                // Handle bookmark button click here
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}