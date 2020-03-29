package com.thiosin.cryptoinfo.domain.models

data class GetCoinsDto(
    val symbol: String,
    val name: String,
    val price: Double,
    val rank: Int,
    val delta24h: Double,
    val iconUrl: String
)