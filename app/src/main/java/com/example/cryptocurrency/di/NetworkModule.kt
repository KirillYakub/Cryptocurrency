package com.example.cryptocurrency.di

import com.example.cryptocurrency.common.Constants
import com.example.cryptocurrency.common.Constants.CONNECTION_TIME_OUT_IN_SECONDS
import com.example.cryptocurrency.common.Constants.READ_TIME_OUT_IN_SECONDS
import com.example.cryptocurrency.data.remote.CoinPaprikaApi
import com.example.cryptocurrency.data.repository.CoinRepositoryImpl
import com.example.cryptocurrency.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideCoinRepository(): CoinRepository {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(CONNECTION_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .readTimeout(READ_TIME_OUT_IN_SECONDS, TimeUnit.SECONDS)
            .build()
        val api = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CoinPaprikaApi::class.java)
        return CoinRepositoryImpl(api)
    }
}