package com.nelsonquintanilla.cryptocurrencyapp.di

import com.nelsonquintanilla.cryptocurrencyapp.common.Constants
import com.nelsonquintanilla.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.nelsonquintanilla.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.nelsonquintanilla.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideCoinPaprikaApi(): CoinPaprikaApi {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    // TODO: Refactor to put this function in another module with Binds
    @Provides
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}