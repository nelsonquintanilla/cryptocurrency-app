package com.nelsonquintanilla.cryptocurrencyapp.data.repository

import com.nelsonquintanilla.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.nelsonquintanilla.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.nelsonquintanilla.cryptocurrencyapp.data.remote.dto.CoinDto
import com.nelsonquintanilla.cryptocurrencyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoin(coinId)
    }
}
