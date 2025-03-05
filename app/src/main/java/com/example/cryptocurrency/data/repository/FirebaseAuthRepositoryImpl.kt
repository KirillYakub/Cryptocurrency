package com.example.cryptocurrency.data.repository

import com.example.cryptocurrency.common.ResultWrapper
import com.example.cryptocurrency.domain.repository.FirebaseAuthRepository
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class FirebaseAuthRepositoryImpl : FirebaseAuthRepository {

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun registerUser(email: String, password: String): Flow<ResultWrapper> {
        return callbackFlow {
            try {
                trySend(ResultWrapper.Loading)
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener { _ ->
                        trySend(ResultWrapper.Success)
                    }
                    .addOnFailureListener { e ->
                        when(e) {
                            is FirebaseNetworkException -> {
                                trySend(ResultWrapper.Error(message = "Connection Unavailable."))
                            }
                            is FirebaseAuthUserCollisionException -> {
                                trySend(ResultWrapper.Error(message = "Email is already in use."))
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                trySend(ResultWrapper.Error(message = "Email address is badly formatted."))
                            }
                            else -> {
                                trySend(ResultWrapper.Error(message = "Something went wrong."))
                            }
                        }
                    }
            } catch (_: Exception) {
                trySend(ResultWrapper.Error(message = "Unknown Error."))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override fun loginUser(email: String, password: String): Flow<ResultWrapper> {
        return callbackFlow {
            try {
                trySend(ResultWrapper.Loading)
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        trySend(ResultWrapper.Success)
                    }
                    .addOnFailureListener { e ->
                        when(e) {
                            is FirebaseNetworkException -> {
                                trySend(ResultWrapper.Error(message = "Connection Unavailable."))
                            }
                            is FirebaseAuthInvalidCredentialsException -> {
                                trySend(ResultWrapper.Error(message = "Incorrect Data."))
                            }
                            else -> {
                                trySend(ResultWrapper.Error(message = "Something went wrong."))
                            }
                        }
                    }
            } catch (_: Exception) {
                trySend(ResultWrapper.Error(message = "Unknown Error."))
            }
            awaitClose()
        }.flowOn(Dispatchers.IO)
    }

    override fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }

    override fun signOutUser() {
        firebaseAuth.signOut()
    }
}