package com.example.cryptocurrency.navigation

import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.example.cryptocurrency.presentation.screens.auth.AuthScreen
import com.example.cryptocurrency.presentation.screens.coin_detail.ui.CoinDetailScreen
import com.example.cryptocurrency.presentation.screens.coin_list.ui.CoinsListScreen
import com.example.cryptocurrency.presentation.screens.on_board.OnBoardScreen
import com.example.cryptocurrency.presentation.screens.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    val activity = LocalActivity.current
    val systemBarsColor = MaterialTheme.colorScheme.background
    SideEffect {
        activity?.window?.statusBarColor = systemBarsColor.toArgb()
        activity?.window?.navigationBarColor = systemBarsColor.toArgb()
    }

    Surface(color = MaterialTheme.colorScheme.background) {
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            navController = navController,
            startDestination = Screen.SplashScreen.route
        ) {
            composable(route = Screen.SplashScreen.route) {
                SplashScreen(navController = navController)
            }
            composable(route = Screen.BoardScreen.route) {
                OnBoardScreen(navController = navController)
            }
            composable(route = Screen.AuthScreen.route) {
                AuthScreen(navController = navController)
            }
            composable(route = Screen.CoinsListScreen.route) {
                CoinsListScreen(navController = navController)
            }
            composable(route = Screen.CoinScreen.route + "/{$PARAM_COIN_ID}") {
                CoinDetailScreen()
            }
        }
    }
}