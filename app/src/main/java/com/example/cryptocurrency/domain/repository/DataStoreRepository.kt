package com.example.cryptocurrency.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveOnBoardingState(completed: Boolean)
    suspend fun saveInputState(input: Boolean)
    fun readOnBoardingState(): Flow<Boolean>
    fun readInputState(): Flow<Boolean>
}