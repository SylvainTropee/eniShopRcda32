package com.example.eni_shop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.eni_shop.ui.screen.ArticleDetailScreen
import com.example.eni_shop.ui.theme.EnishopTheme

private const val TAG = "MainActivity"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            EnishopTheme {
                ArticleDetailScreen(articleId = 2)
            }
        }
    }
}

