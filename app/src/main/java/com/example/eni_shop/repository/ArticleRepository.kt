package com.example.eni_shop.repository

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DaoFactory
import com.example.eni_shop.dao.DaoType

object ArticleRepository {

    private val articleDAO : ArticleDAO = DaoFactory.createArticleDao(DaoType.MEMORY);

    fun getArticle(id : Long) : Article? {
        return articleDAO.findById(id)
    }

    fun addArticle(article : Article) : Long{
        return articleDAO.insert(article)
    }

}