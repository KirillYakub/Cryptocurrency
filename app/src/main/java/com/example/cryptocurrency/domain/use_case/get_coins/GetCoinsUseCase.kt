package com.example.cryptocurrency.domain.use_case.get_coins

import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.data.remote.dto.coin.toCoin
import com.example.cryptocurrency.domain.model.Coin
import com.example.cryptocurrency.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(private val repository: CoinRepository) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coins))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred!"))
        }
    }
}