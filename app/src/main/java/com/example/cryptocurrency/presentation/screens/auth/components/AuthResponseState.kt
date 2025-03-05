package com.example.cryptocurrency.presentation.screens.auth.components

data class AuthResponseState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)