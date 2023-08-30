package com.example.cryptocurrency.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.presentation.coin_detail.ui.CoinDetailScreen
import com.example.cryptocurrency.presentation.coin_list.ui.CoinListScreen
import com.example.cryptocurrency.presentation.ui.theme.CryptocurrencyAppYTTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CryptocurrencyAppYTTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CoinsListScreen.route
                    ) {
                        composable(route = Screen.CoinsListScreen.route) {
                            CoinListScreen(navController)
                        }
                        composable(route = Screen.CoinScreen.route + "/{coinId}") {
                            CoinDetailScreen()
                        }
                    }
                }
            }
        }
    }
}