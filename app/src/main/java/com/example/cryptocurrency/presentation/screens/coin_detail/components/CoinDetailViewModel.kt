package com.example.cryptocurrency.presentation.screens.coin_detail.components

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.Resource
import com.example.cryptocurrency.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val coinUseCase: GetCoinUseCase,
    private val savesStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        onCoinDetailLoadOrRefresh()
    }

    fun onCoinDetailLoadOrRefresh() {
        savesStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { getCoinDetail(it) }
    }

    private fun getCoinDetail(coinId: String) {
        coinUseCase(coinId).onEach { result ->
            when(result) {
                is Resource.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailState(error = result.message)
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}