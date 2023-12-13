package com.capstone.beruang.data

class ArticleRepository {
    private val dummyData = mutableListOf<Article>()

    init {
        if (dummyData.isEmpty()) {
            ArticleData.articleList.forEach {
                dummyData.add(it)
            }
        }
    }
}