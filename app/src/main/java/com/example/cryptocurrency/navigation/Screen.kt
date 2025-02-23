package com.example.cryptocurrency.navigation

sealed class Screen(val route: String) {
    data object SplashScreen: Screen("splash_screen")
    data object BoardScreen: Screen("on_board_screen")
    data object CoinsListScreen: Screen("coins_screen")
    data object CoinScreen: Screen("coin_details_screen")
}
