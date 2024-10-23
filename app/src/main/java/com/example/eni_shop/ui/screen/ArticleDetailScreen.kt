package com.example.eni_shop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.utils.toFrenchDate


@Composable
fun ArticleDetailScreen(modifier: Modifier = Modifier) {

    Scaffold(topBar = { EniShopTopBar() }) {
        Column(modifier = Modifier.padding(it)) {
            ArticleDetail(ArticleRepository.getArticle(2)!!)
        }
    }
}

@Composable
fun ArticleDetail(article: Article, modifier: Modifier = Modifier) {

    Column {
        Text(
            text = article.name,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp),
            lineHeight = 1.em,
            textAlign = TextAlign.Justify
        )
        Surface(
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier.size(200.dp)
            )
        }
        Text(
            text = article.description,
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Justify,
            style = MaterialTheme.typography.bodyMedium
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier.fillMaxWidth().padding(16.dp),
        ) {
            Text(text = "Prix ${article.price} €")
            Text(text = "Date de sortie : ${article.date.toFrenchDate()}")
        }
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
            ) {
            Checkbox(checked = true, onCheckedChange = {})
            Text(text = "Favoris ?")
        }

    }

}

@Composable
@Preview
fun Preview() {

    ArticleDetail(ArticleRepository.getArticle(1)!!)
}