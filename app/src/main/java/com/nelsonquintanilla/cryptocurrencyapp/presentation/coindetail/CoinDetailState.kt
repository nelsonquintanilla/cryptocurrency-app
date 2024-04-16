package com.nelsonquintanilla.cryptocurrencyapp.presentation.coindetail

import com.nelsonquintanilla.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coinDetail: CoinDetail? = null,
    val error: String = ""
)
