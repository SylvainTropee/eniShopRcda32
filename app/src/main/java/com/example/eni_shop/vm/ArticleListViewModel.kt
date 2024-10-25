package com.example.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleListViewModel(private val articleRepository : ArticleRepository) : ViewModel() {

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories : StateFlow<List<String>>
        get() = _categories

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles : StateFlow<List<Article>> = _articles

    init {
        _articles.value = articleRepository.getAllArticles()
        _categories.value = listOf("electronics", "jewelery", "men's clothing", "women's clothing");
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                //val application = checkNotNull(extras[APPLICATION_KEY])

                return ArticleListViewModel(ArticleRepository()) as T
            }
        }
    }
}