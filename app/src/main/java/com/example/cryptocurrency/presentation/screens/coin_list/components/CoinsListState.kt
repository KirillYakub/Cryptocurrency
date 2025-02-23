package com.example.cryptocurrency.presentation.screens.coin_list.components

import com.example.cryptocurrency.domain.model.Coin

data class CoinsListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String? = null
)
