package com.example.cryptocurrency.di

import com.example.cryptocurrency.data.repository.FirebaseAuthRepositoryImpl
import com.example.cryptocurrency.domain.repository.FirebaseAuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(): FirebaseAuthRepository {
        return FirebaseAuthRepositoryImpl()
    }
}