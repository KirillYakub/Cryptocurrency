package com.example.cryptocurrency.domain.use_case.read_input_state

import com.example.cryptocurrency.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadInputUseCase @Inject constructor(
    private val repository: DataStoreRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readInputState()
    }
}