package com.example.cryptocurrency.domain.use_case.save_on_boarding

import com.example.cryptocurrency.domain.repository.DataStoreRepository
import javax.inject.Inject

class SaveOnBoardingUseCase @Inject constructor(
    private val repository: DataStoreRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnBoardingState(completed)
    }
}