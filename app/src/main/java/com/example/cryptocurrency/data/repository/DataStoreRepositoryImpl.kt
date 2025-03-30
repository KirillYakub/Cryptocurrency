package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow

class DataStoreRepositoryImpl(
    private val dataStoreOperations: DataStoreOperations
): DataStoreRepository {

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStoreOperations.saveOnBoardingState(completed)
    }

    override suspend fun saveInputState(input: Boolean) {
        dataStoreOperations.saveInputState(input)
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStoreOperations.readOnBoardingState()
    }

    override fun readInputState(): Flow<Boolean> {
        return dataStoreOperations.readInputState()
    }
}