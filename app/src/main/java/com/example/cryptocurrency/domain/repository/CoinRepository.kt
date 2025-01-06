package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.data.remote.dto.coin.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.coin.CoinDto

interface CoinRepository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoin(coinId: String): CoinDetailDto
}