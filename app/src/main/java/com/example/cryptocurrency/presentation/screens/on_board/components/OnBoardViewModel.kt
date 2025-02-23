package com.example.cryptocurrency.presentation.screens.on_board.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.use_case.save_on_boarding.SaveOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardViewModel @Inject constructor(
    private val saveOnBoardingUseCase: SaveOnBoardingUseCase
): ViewModel() {

    var isCompletedSaved by mutableStateOf(false)
        private set

    fun saveOnBoardingState(completed: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveOnBoardingUseCase(completed = completed)
            isCompletedSaved = true
        }
    }
}