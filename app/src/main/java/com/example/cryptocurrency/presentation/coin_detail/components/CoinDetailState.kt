package com.example.cryptocurrency.presentation.coin_detail.components

import com.example.cryptocurrency.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = ""
)
