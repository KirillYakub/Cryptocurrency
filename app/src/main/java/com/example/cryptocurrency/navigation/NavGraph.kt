package com.example.cryptocurrency.navigation

import android.content.pm.ActivityInfo
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cryptocurrency.common.Constants.PARAM_COIN_ID
import com.example.cryptocurrency.presentation.screens.auth.AuthScreen
import com.example.cryptocurrency.presentation.screens.coin_detail.ui.CoinDetailScreen
import com.example.cryptocurrency.presentation.screens.coin_list.ui.CoinsListScreen
import com.example.cryptocurrency.presentation.screens.on_board.OnBoardScreen
import com.example.cryptocurrency.presentation.screens.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    val stackEntry = navController.currentBackStackEntryAsState()
    val route = stackEntry.value?.destination?.route

    UIConfiguration(route = route)

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

@Composable
fun UIConfiguration(route: String?) {
    val activity = LocalActivity.current
    val defaultSystemBarsColor = MaterialTheme.colorScheme.background
    val authStatusBarColor = MaterialTheme.colorScheme.onPrimaryContainer
    SideEffect {
        activity?.apply {
            window?.statusBarColor =
                if(route == Screen.AuthScreen.route) authStatusBarColor.toArgb() else defaultSystemBarsColor.toArgb()
            window?.navigationBarColor = defaultSystemBarsColor.toArgb()
            requestedOrientation =
                if(route == Screen.AuthScreen.route) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT else ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }
}