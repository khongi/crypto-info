package com.thiosin.cryptoinfo.ui.list.models

data class ListCoin(
    val symbol: String,
    val name: String,
    val price: String,
    val rank: String,
    val delta24h: String,
    val iconUrl: String,
    val deltaTextColor: Int
)