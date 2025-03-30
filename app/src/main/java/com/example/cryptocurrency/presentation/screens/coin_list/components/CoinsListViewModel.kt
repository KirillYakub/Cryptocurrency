package com.example.cryptocurrency.presentation.screens.coin_list.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.repository.FirebaseAuthRepository
import com.example.cryptocurrency.domain.use_case.get_coins.GetCoinsUseCase
import com.example.cryptocurrency.domain.use_case.save_input_state.SaveInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CoinsListViewModel @Inject constructor(
    private val coinsUseCase: GetCoinsUseCase,
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val saveInputUseCase: SaveInputUseCase
): ViewModel() {

    private val _coinsState = mutableStateOf(CoinsListState())
    val coinsState: State<CoinsListState> = _coinsState

    private val _signOutState = mutableStateOf(false)
    val signOurState: State<Boolean> = _signOutState

    init {
        onCoinsLoadOrRefresh()
    }

    fun onCoinsLoadOrRefresh() {
        getCoins()
    }

    fun signOutUser() {
        viewModelScope.launch(Dispatchers.IO) {
            _coinsState.value = CoinsListState(isLoading = true)
            firebaseAuthRepository.signOutUser()
            saveInputUseCase(input = false)
            delay(2000)
            _signOutState.value = true
        }
    }

    private fun getCoins() {
        coinsUseCase().onEach {result ->
            when(result) {
                is Resource.Success -> {
                    _coinsState.value = CoinsListState(coins = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _coinsState.value = CoinsListState(error = result.message)
                }
                is Resource.Loading -> {
                    _coinsState.value = CoinsListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}