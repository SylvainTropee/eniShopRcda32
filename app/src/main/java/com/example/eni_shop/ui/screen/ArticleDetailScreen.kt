package com.example.eni_shop.ui.screen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.repository.ArticleRepository
import com.example.eni_shop.ui.common.EniShopScaffold
import com.example.eni_shop.utils.toFrenchDate
import com.example.eni_shop.vm.ArticleDetailViewModel


@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    articleId: Long,
    navigationIcon: @Composable () -> Unit,
    articleDetailViewModel: ArticleDetailViewModel = viewModel(factory = ArticleDetailViewModel.Factory)
) {
    val article by articleDetailViewModel.currentArticle.collectAsState()
    val isArticleFav by articleDetailViewModel.isArticleFav.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        articleDetailViewModel.getArticleById(articleId)
        articleDetailViewModel.getArticleFav(articleId)
    }

    EniShopScaffold(navigationIcon = navigationIcon) {
        Column() {
            article?.let {
                ArticleDetail(
                    article = it,
                    isArticleFav = isArticleFav,
                    onArticleFavChange = { isChecked ->
                        if (isChecked) {
                            articleDetailViewModel.saveArticleFav()
                            Toast.makeText(context, "Article ajouté au favoris", Toast.LENGTH_SHORT).show()
                        } else {
                            articleDetailViewModel.deleteArticleFav()
                            Toast.makeText(context, "Article supprimé des favoris", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun ArticleDetail(
    article: Article,
    isArticleFav: Boolean,
    onArticleFavChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current

    Column {
        Text(
            text = article.name,
            fontSize = 30.sp,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(16.dp)
                .testTag("ArticleName")
                .clickable {
//                    Intent(Intent.ACTION_WEB_SEARCH).also {
//                        it.putExtra(SearchManager.QUERY, article.name)
//                        context.startActivity(it)
//                    }

                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.google.com/search?q=${article.name}+eni+shop")
                    ).also {
                        context.startActivity(it)
                    }
                },
            lineHeight = 1.em,
            textAlign = TextAlign.Justify,

            )
        Surface(
            color = MaterialTheme.colorScheme.inversePrimary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = "Prix ${article.price} €")
            Text(text = "Date de sortie : ${article.date.toFrenchDate()}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Checkbox(checked = isArticleFav, onCheckedChange = onArticleFavChange)
            Text(text = "Favoris ?")
        }

    }

}

@Composable
@Preview
fun Preview() {

    //ArticleDetail(ArticleRepository().getArticle(1)!!)
}