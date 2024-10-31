package com.example.eni_shop.dao

import com.example.eni_shop.dao.memory.ArticleDAOMemoryImpl

abstract class DaoFactory {

    companion object {

        fun createArticleDao(type: DaoType): ArticleDAO {

            val dao: ArticleDAO

            when (type) {
                DaoType.MEMORY -> dao = ArticleDAOMemoryImpl()
                DaoType.NETWORK -> TODO()
                DaoType.ROOM -> TODO()
            }

            return dao;
        }
    }


}