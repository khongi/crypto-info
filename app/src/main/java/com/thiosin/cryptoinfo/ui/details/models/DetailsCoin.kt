package com.thiosin.cryptoinfo.ui.details.models

data class DetailsCoin(
    val symbol: String,
    val name: String,
    val price: String,
    val rank: String,
    val iconUrl: String,
    val low24h: String,
    val high24h: String,
    val delta1h: DetailsCoinDelta?,
    val delta24h: DetailsCoinDelta,
    val delta7d: DetailsCoinDelta?
)