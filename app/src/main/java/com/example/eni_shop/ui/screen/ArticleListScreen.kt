package com.example.eni_shop.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.eni_shop.bo.Article
import com.example.eni_shop.ui.common.EniShopScaffold
import com.example.eni_shop.vm.ArticleListViewModel

@Composable
fun ArticleListScreen(
    articleListViewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory),
    onNavigateToAddArticle: () -> Unit,
    onNavigateToArticleDetail: (Long) -> Unit
) {

    val articles by articleListViewModel.articles.collectAsState()
    val categories by articleListViewModel.categories.collectAsState()

    var category by remember {
        mutableStateOf("")
    }

    val filteredArticles = if (category != "") {
        articles.filter {
            it.category == category
        }
    } else {
        articles
    }

    EniShopScaffold(
        floatingActionButton = { ArticleListFAB(onNavigateToAddArticle = onNavigateToAddArticle) }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
        ) {
            CategoryFilterChip(
                categories = categories,
                selectedCategory = category,
                onCategoryChange = { selectedCategory ->
                    category = selectedCategory
                }
            )
            ArticleList(
                articles = filteredArticles,
                onNavigateToArticleDetail = onNavigateToArticleDetail
            )
        }
    }


}

@Composable
fun ArticleList(
    articles: List<Article>,
    onNavigateToArticleDetail: (Long) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(articles) { article ->
            ArticleItem(article = article, onNavigateToArticleDetail = onNavigateToArticleDetail)
        }
    }


}

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onNavigateToArticleDetail: (Long) -> Unit
) {

    Card(
        modifier = Modifier.clickable {
            onNavigateToArticleDetail(article.id)
        },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .border(1.dp, MaterialTheme.colorScheme.inverseSurface, CircleShape)
                    .padding(8.dp)
            )
            Text(
                text = article.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
            Text(text = "${String.format("%.2f", article.price)} €")

        }

    }

}

@Composable
fun CategoryFilterChip(
    categories: List<String>,
    selectedCategory: String,
    onCategoryChange: (String) -> Unit
) {

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(categories) { category ->
            FilterChip(
                selected = selectedCategory == category,
                onClick = {
                    if (selectedCategory != category) {
                        onCategoryChange(category)
                    } else {
                        onCategoryChange("")
                    }

                },
                label = { Text(text = category) },
                leadingIcon = if (selectedCategory == category) {
                    { Icon(imageVector = Icons.Default.Done, contentDescription = null) }
                } else {
                    null
                }
            )
        }
    }
}

@Composable
fun ArticleListFAB(onNavigateToAddArticle: () -> Unit) {

    FloatingActionButton(
        onClick = onNavigateToAddArticle,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 4.dp)
    ) {
        Image(
            imageVector = Icons.Default.Add,
            contentDescription = "Add article",
            modifier = Modifier.size(40.dp)
        )
    }

}


@Composable
@Preview
fun ArticleListPreview() {
    // ArticleListScreen()
}