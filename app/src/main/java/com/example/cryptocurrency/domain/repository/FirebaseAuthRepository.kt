package com.example.cryptocurrency.domain.repository

import com.example.cryptocurrency.common.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthRepository {
    fun registerUser(email: String, password: String): Flow<ResultWrapper>
    fun loginUser(email: String, password: String): Flow<ResultWrapper>
    fun isUserLoggedIn(): Boolean
    fun signOutUser()
}