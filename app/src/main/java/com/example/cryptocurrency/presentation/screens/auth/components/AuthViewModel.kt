package com.example.cryptocurrency.presentation.screens.auth.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptocurrency.common.ResultWrapper
import com.example.cryptocurrency.domain.repository.FirebaseAuthRepository
import com.example.cryptocurrency.domain.use_case.validate_auth_form.validate_email.ValidateEmail
import com.example.cryptocurrency.domain.use_case.validate_auth_form.validate_password.ValidatePassword
import com.example.cryptocurrency.presentation.screens.auth.components.form.AuthFormEvent
import com.example.cryptocurrency.presentation.screens.auth.components.form.AuthFormState
import com.example.cryptocurrency.domain.model.AuthType
import com.example.cryptocurrency.domain.use_case.save_input_state.SaveInputUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val saveInputUseCase: SaveInputUseCase
) : ViewModel() {

    private val validateEmail = ValidateEmail()
    private val validatePassword = ValidatePassword()

    var authResponseState by mutableStateOf(AuthResponseState())
        private set
    var authFormState by mutableStateOf(AuthFormState())
        private set

    init {
        authResponseState = AuthResponseState(isLoading = false)
        if(firebaseAuthRepository.isUserLoggedIn()) {
            authResponseState = AuthResponseState(isSuccess = true)
        }
        else {
            authResponseState = AuthResponseState(isLoading = false)
        }
    }

    fun onEvent(event: AuthFormEvent) {
        when(event) {
            is AuthFormEvent.AnonymousInput -> {
                saveAnonymousInput(input = event.input)
            }
            is AuthFormEvent.AuthTypeChanged -> {
                authFormState = AuthFormState(authType = event.authType)
            }
            is AuthFormEvent.EmailChanged -> {
                authFormState = authFormState.copy(email = event.email)
            }
            is AuthFormEvent.PasswordChange -> {
                authFormState = authFormState.copy(password = event.password)
            }
            AuthFormEvent.OnEmailClear -> {
                authFormState = authFormState.copy(email = "")
            }
            AuthFormEvent.OnPasswordClear -> {
                authFormState = authFormState.copy(password = "")
            }
            AuthFormEvent.Submit -> {
                when(authFormState.authType) {
                    AuthType.SIGN_IN -> signInWithData()
                    AuthType.SIGN_UP -> submitData()
                }
            }
        }
    }

    private fun saveAnonymousInput(input: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            saveInputUseCase(input = input)
            authFormState = authFormState.copy(isAnonymousInput = input)
        }
    }

    private fun submitData() {
        val emailResult = validateEmail(email = authFormState.email)
        val passwordResult = validatePassword(password = authFormState.password)
        if(emailResult.error != null || passwordResult.error != null) {
            authFormState = authFormState.copy(
                emailError = emailResult.error,
                passwordError = passwordResult.error
            )
            authResponseState = AuthResponseState()
        }
        else
            signUpWithData()
    }

    private fun signInWithData() {
        firebaseAuthRepository.loginUser(
            email = authFormState.email,
            password = authFormState.password
        ).onEach { result ->
            when(result) {
                is ResultWrapper.Success -> {
                    authResponseState = AuthResponseState(isSuccess = true)
                }
                is ResultWrapper.Error -> {
                    authFormState = authFormState.copy(
                        emailError = null,
                        passwordError = null
                    )
                    authResponseState = AuthResponseState(error = result.message)
                }
                is ResultWrapper.Loading -> {
                    authResponseState = AuthResponseState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun signUpWithData() {
        firebaseAuthRepository.registerUser(
            email = authFormState.email,
            password = authFormState.password
        ).onEach { result ->
            when(result) {
                is ResultWrapper.Success -> {
                    authResponseState = AuthResponseState(isSuccess = true)
                }
                is ResultWrapper.Error -> {
                    authFormState = authFormState.copy(
                        emailError = null,
                        passwordError = null
                    )
                    authResponseState = AuthResponseState(error = result.message)
                }
                is ResultWrapper.Loading -> {
                    authResponseState = AuthResponseState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}