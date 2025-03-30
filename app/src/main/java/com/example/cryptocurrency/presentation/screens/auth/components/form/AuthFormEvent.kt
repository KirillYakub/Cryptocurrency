package com.example.cryptocurrency.presentation.screens.auth.components.form

import com.example.cryptocurrency.domain.model.AuthType

sealed interface AuthFormEvent {
    data class AnonymousInput(val input: Boolean): AuthFormEvent
    data class AuthTypeChanged(val authType: AuthType): AuthFormEvent
    data class EmailChanged(val email: String) : AuthFormEvent
    data class PasswordChange(val password: String) : AuthFormEvent
    data object OnEmailClear : AuthFormEvent
    data object OnPasswordClear : AuthFormEvent
    data object Submit: AuthFormEvent
}