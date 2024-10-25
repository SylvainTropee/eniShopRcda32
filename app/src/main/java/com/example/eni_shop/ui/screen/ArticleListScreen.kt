package com.example.eni_shop.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
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
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.eni_shop.ui.common.EniShopTopBar
import com.example.eni_shop.vm.ArticleListViewModel
import java.util.Date

@Composable
fun ArticleListScreen(
    articleListViewModel: ArticleListViewModel = viewModel(factory = ArticleListViewModel.Factory)) {

    val articles by articleListViewModel.articles.collectAsState()
    val categories by articleListViewModel.categories.collectAsState()

    Scaffold(
        topBar = { EniShopTopBar() }
    ) {
        Column(modifier = Modifier.padding(it)) {
            CategoryFilterChip(categories = categories)
            ArticleList(articles = articles)
        }
    }


}

@Composable
fun ArticleList(
    articles : List<Article>
) {



    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(articles) { article ->
            ArticleItem(article = article)
        }
    }


}

@Composable
fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = article.urlImage,
                contentDescription = article.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.inverseSurface, CircleShape)
            )
            Text(
                text = article.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Justify,
                modifier = Modifier.padding(8.dp)
            )
            Text(text = "${article.price} €")

        }

    }

}

@Composable
fun CategoryFilterChip(
   categories : List<String>
) {



    LazyRow() {
        items(categories) {
            FilterChip(
                selected = false,
                onClick = {},
                label = { Text(text = it) }
            )
        }
    }
}

@Composable
@Preview
fun ArticleListPreview() {
    ArticleItem(
        article = Article(
            id = 3,
            name = "Mens Cotton Jacket - Mens Casual Premium Slim Fit T-Shirts",
            description = "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your familymember. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.",
            price = 55.99,
            urlImage = "https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg",
            category = "men's clothing",
            date = Date(),
        )
    )
}