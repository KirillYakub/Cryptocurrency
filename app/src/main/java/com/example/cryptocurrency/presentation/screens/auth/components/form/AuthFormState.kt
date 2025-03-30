package com.example.cryptocurrency.presentation.screens.auth.components.form

import com.example.cryptocurrency.domain.model.AuthType

data class AuthFormState(
    val authType: AuthType = AuthType.SIGN_UP,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isAnonymousInput: Boolean = false
)