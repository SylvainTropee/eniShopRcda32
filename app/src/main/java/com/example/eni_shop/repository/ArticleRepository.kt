package com.example.eni_shop.repository

import com.example.eni_shop.bo.Article
import com.example.eni_shop.dao.ArticleDAO
import com.example.eni_shop.dao.DaoFactory
import com.example.eni_shop.dao.DaoType
import com.example.eni_shop.services.ArticleService

class ArticleRepository(
    private val articleDaoRoomImpl: ArticleDAO,
    private val articleService: ArticleService
) {
    suspend fun getArticle(id: Long, type: DaoType = DaoType.NETWORK): Article? {
        return when (type) {
            DaoType.NETWORK -> articleService.getArticleById(id)
            else -> articleDaoRoomImpl.findById(id)
        }
    }

    suspend fun getAllArticles(type: DaoType = DaoType.NETWORK): List<Article> {
        return when (type) {
            DaoType.NETWORK -> articleService.getAllArticles()
            else -> articleDaoRoomImpl.findAll()
        }
    }

    suspend fun addArticle(article: Article, type: DaoType = DaoType.NETWORK): Any {
        return when (type) {
            DaoType.NETWORK -> articleService.addArticle(article)
            else -> articleDaoRoomImpl.insert(article)
        }
    }

    suspend fun getAllCategories(): List<String> {
        return articleService.getAllCategories()
    }

    fun deleteArticle(article: Article, type: DaoType = DaoType.NETWORK) {
        articleDaoRoomImpl.delete(article)
    }

//    //méthode ROOM
//    fun getArticleFav(id : Long) : Article?{
//        return articleDaoRoomImpl.findById(id)
//    }
//    fun deleteArticleFav(article: Article){
//        articleDaoRoomImpl.delete(article)
//    }
//    fun addArticleFav(article : Article) : Long{
//        return articleDaoRoomImpl.insert(article)
//    }


}