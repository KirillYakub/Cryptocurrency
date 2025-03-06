package com.example.cryptocurrency.presentation.screens.auth.components.form

import com.example.cryptocurrency.domain.model.AuthType

data class AuthFormState(
    val authType: AuthType = AuthType.SIGN_UP,
    val email: String = "kirillyaku@gmail.com",
    val emailError: String? = null,
    val password: String = "Hilumulu2303",
    val passwordError: String? = null
)