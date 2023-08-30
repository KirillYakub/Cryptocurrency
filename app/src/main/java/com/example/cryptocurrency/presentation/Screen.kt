package com.example.cryptocurrency.presentation

sealed class Screen(val route: String) {
    data object CoinsListScreen: Screen("coins_screen")
    data object CoinScreen: Screen("coin_details_screen")
}
