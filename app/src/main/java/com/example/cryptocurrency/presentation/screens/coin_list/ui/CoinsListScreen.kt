package com.example.cryptocurrency.presentation.screens.coin_list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.navigation.Screen
import com.example.cryptocurrency.presentation.screens.coin_list.components.CoinsListViewModel
import com.example.cryptocurrency.presentation.common.EmptyScreen
import com.example.cryptocurrency.presentation.ui.theme.COINS_LIST_SPACER_HEIGHT

@Composable
fun CoinsListScreen(
    navController: NavController,
    viewModel: CoinsListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state.isLoading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    strokeWidth = 5.dp
                )
            }
            state.error != null -> {
                EmptyScreen(
                    message = state.error,
                    onRefresh = viewModel::onCoinsLoadOrRefresh
                )
            }
            else -> {
                CoinsList(
                    coins = state.coins,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun CoinsList(
    coins: List<Coin>,
    navController: NavController
) {
    Scaffold(
        topBar = {
            CoinsListTopBar()
        },
        containerColor = Color.Transparent
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
        ) {
            LazyColumn {
                items(coins) { coin ->
                    CoinsListItem(
                        coin = coin,
                        onCoinClick = {
                            navController.navigate(Screen.CoinScreen.route + "/${coin.id}")
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(COINS_LIST_SPACER_HEIGHT)
                    .background(
                        Brush.verticalGradient(
                            listOf(
                                MaterialTheme.colorScheme.background,
                                Color.Transparent
                            )
                        )
                    )
            )
        }
    }
}