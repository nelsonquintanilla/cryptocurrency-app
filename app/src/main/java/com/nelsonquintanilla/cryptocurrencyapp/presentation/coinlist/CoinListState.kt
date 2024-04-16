package com.nelsonquintanilla.cryptocurrencyapp.presentation.coinlist

import com.nelsonquintanilla.cryptocurrencyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)
