package com.thiosin.cryptoinfo.domain.models

data class GetCoinDto(
    val symbol: String,
    val price: Double,
    val low24h: Double,
    val high24h: Double,
    val delta1h: Double,
    val delta24h: Double,
    val delta7d: Double
)