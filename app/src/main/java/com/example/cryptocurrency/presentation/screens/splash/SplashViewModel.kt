package com.example.cryptocurrency.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.domain.use_case.read_input_state.ReadInputUseCase
import com.example.cryptocurrency.domain.use_case.read_on_boarding.ReadOnBoardingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readOnBoardingUseCase: ReadOnBoardingUseCase,
    private val readInputUseCase: ReadInputUseCase
): ViewModel() {

    private val _isOnBoardingCompleted = MutableStateFlow(false)
    val isOnBoardingCompleted: StateFlow<Boolean> = _isOnBoardingCompleted

    private val _isInputAnonymously = MutableStateFlow(false)
    val isInputAnonymously: StateFlow<Boolean> = _isInputAnonymously

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _isOnBoardingCompleted.value =
                readOnBoardingUseCase().stateIn(viewModelScope).value
            _isInputAnonymously.value =
                readInputUseCase().stateIn(viewModelScope).value
        }
    }
}