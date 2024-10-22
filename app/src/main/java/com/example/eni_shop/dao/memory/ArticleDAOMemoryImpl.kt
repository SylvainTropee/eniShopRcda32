package com.example.eni_shop.dao.memory

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import java.util.Date

class ArticleDAOMemoryImpl : ArticleDAO {

    val articlesInMemory: MutableList<Article> = mutableListOf(
        Article(
            id = 1,
            name = "Ecran",
            description = "Description c'est un écran",
            price = 152.56,
            category = "Multimédia",
            urlImage = "default",
            date = Date()
        ),
        Article(
            id = 2,
            name = "Ecran2",
            description = "Description c'est un écran2",
            price = 52.56,
            category = "Multimédia2",
            urlImage = "default2",
            date = Date()
        )
    )


    override fun findById(id: Long): Article? {
        return articlesInMemory.find {
            it.id == id
        }

    }

    override fun insert(article: Article): Long {
        article.id = articlesInMemory.size.toLong() + 1
        articlesInMemory.add(article)
        return article.id
    }


}