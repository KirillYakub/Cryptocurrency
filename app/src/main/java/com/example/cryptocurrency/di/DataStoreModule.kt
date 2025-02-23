package com.example.cryptocurrency.di

import android.content.Context
import com.example.cryptocurrency.data.repository.DataStoreOperations
import com.example.cryptocurrency.data.repository.DataStoreRepositoryImpl
import com.example.cryptocurrency.domain.repository.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ): DataStoreRepository {
        val dataStoreOperations = DataStoreOperations(context)
        return DataStoreRepositoryImpl(dataStoreOperations)
    }
}