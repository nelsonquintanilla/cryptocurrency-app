package com.nelsonquintanilla.cryptocurrencyapp.di

import com.nelsonquintanilla.cryptocurrencyapp.common.Constants
import com.nelsonquintanilla.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.nelsonquintanilla.cryptocurrencyapp.data.repository.CoinRepositoryImpl
import com.nelsonquintanilla.cryptocurrencyapp.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpCallFactory(): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor()
                    .apply {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
            )
            .build()
    }

    @Provides
    fun providesRetrofit(okhttpCallFactory: dagger.Lazy<Call.Factory>): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            // I use callFactory lambda here with dagger.Lazy<Call.Factory>
            // to prevent initializing OkHttp on the main thread.
            .callFactory { okhttpCallFactory.get().newCall(it) }
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providesCoinPaprikaApi(retrofit: Retrofit): CoinPaprikaApi {
        return retrofit.create(CoinPaprikaApi::class.java)
    }

    // TODO: Refactor to put this function in another module with Binds
    @Provides
    fun provideCoinRepository(api: CoinPaprikaApi): CoinRepository {
        return CoinRepositoryImpl(api)
    }
}