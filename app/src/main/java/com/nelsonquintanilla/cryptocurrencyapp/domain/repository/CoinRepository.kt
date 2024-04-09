package com.nelsonquintanilla.cryptocurrencyapp.domain.repository

import com.nelsonquintanilla.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.nelsonquintanilla.cryptocurrencyapp.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}
