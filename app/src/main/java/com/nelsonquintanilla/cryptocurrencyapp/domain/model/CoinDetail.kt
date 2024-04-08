package com.nelsonquintanilla.cryptocurrencyapp.domain.model

import com.nelsonquintanilla.cryptocurrencyapp.data.remote.dto.TeamMember

data class CoinDetail(
    val description: String,
    val id: String,
    val isActive: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMember>
)
