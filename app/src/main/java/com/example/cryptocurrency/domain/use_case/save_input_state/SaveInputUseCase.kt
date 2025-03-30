package com.example.cryptocurrency.domain.use_case.save_input_state

import com.example.cryptocurrency.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveInputUseCase @Inject constructor(
    private val repository: DataStoreRepository
) {
    suspend operator fun invoke(input: Boolean) {
        repository.saveInputState(input)
    }
}