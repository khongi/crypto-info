package com.thiosin.cryptoinfo.domain.models

data class DomainCoin(
    val symbol: String,
    val name: String,
    val price: Double,
    val rank: Int,
    val delta24h: Double,
    val iconUrl: String,
    val low24h: Double? = null,
    val high24h: Double? = null,
    val delta1h: Double? = null,
    val delta7d: Double? = null
)