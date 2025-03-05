package com.example.cryptocurrency.common

sealed interface ResultWrapper {
    data object Success: ResultWrapper
    data class Error(val message: String): ResultWrapper
    data object Loading: ResultWrapper
}