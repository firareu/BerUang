// DetailArticleActivity.kt
package com.capstone.beruang.ui.article.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.capstone.beruang.databinding.ActivityDetailArticleBinding
import com.capstone.beruang.data.Article

class DetailArticleActivity : AppCompatActivity() {

    companion object {
        const val ARTICLE_KEY = "article"
    }

    private lateinit var binding: ActivityDetailArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve article data from intent
        val article = intent.getParcelableExtra<Article>(ARTICLE_KEY)

        // Use article data to populate the UI (title, image, content, etc.)
        if (article != null) {
            binding.articleTitle.text = article.title

            // Assuming you have an ImageView in your layout with id "imageView"
            Glide.with(this)
                .load(article.image)
                .into(binding.imageArticle)

            binding.articleContent.text = article.content
        }
    }
}
