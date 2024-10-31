package com.example.eni_shop.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.eni_shop.bo.Article
import com.example.eni_shop.db.EniShopDatabase
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.services.ArticleService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticleFormViewModel(private val articleRepository: ArticleRepository) : ViewModel() {

    private val _name = MutableStateFlow("")
    var name = _name.asStateFlow()

    fun setName(name : String){
        _name.value = name
    }

    private val _description = MutableStateFlow("")
    var description = _description.asStateFlow()

    private val _price = MutableStateFlow(0.0)
    var price = _price.asStateFlow()

    private val _category = MutableStateFlow("")
    var category = _category.asStateFlow()

    fun addArticle(){

        val article = Article(
            name = _name.value,
            description = _description.value,
            price = _price.value,
            category = _category.value
        )

        viewModelScope.launch {
            articleRepository.addArticle(article)
        }
    }

    companion object {

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                // Get the Application object from extras
                val application = checkNotNull(extras[APPLICATION_KEY])

                return ArticleFormViewModel(
                    ArticleRepository(
                        EniShopDatabase.getInstance(application.applicationContext).getArticleDAO(),
                        ArticleService.ShopApi.retrofitService
                    )
                ) as T
            }
        }
    }
}