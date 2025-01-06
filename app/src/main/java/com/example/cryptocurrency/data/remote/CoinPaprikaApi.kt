package com.example.cryptocurrency.data.remote

import com.example.cryptocurrency.data.remote.dto.coin.CoinDetailDto
import com.example.cryptocurrency.data.remote.dto.coin.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoin(@Path("coinId") coinId: String): CoinDetailDto
}