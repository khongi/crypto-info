package com.thiosin.cryptoinfo.common

import com.thiosin.cryptoinfo.data.disk.models.RoomCoin
import com.thiosin.cryptoinfo.domain.models.DomainCoin

val ROOM_COINS = listOf(
    RoomCoin(
        symbol = "BTC",
        name = "Bitcoin",
        price = 7312.0701358619,
        rank = 1,
        delta1h = -0.19,
        delta24h = -0.53,
        delta7d = 15.68,
        low24h = 7114.7503569226,
        high24h = 7428.0425412873,
        iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
    ),
    RoomCoin(
        symbol = "ETH",
        name = "Ethereum",
        price = 170.0376093918,
        rank = 2,
        delta1h = -0.56,
        delta24h = -1.58,
        delta7d = 27.52,
        low24h = 163.3306438671,
        high24h = 174.8423532766,
        iconUrl = "https://assets.coinlayer.com/icons/ETH.png"
    )
)

val DOMAIN_COINS = listOf(
    DomainCoin(
        symbol = "BTC",
        name = "Bitcoin",
        price = 7312.0701358619,
        rank = 1,
        delta1h = -0.19,
        delta24h = -0.53,
        delta7d = 15.68,
        low24h = 7114.7503569226,
        high24h = 7428.0425412873,
        iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
    ),
    DomainCoin(
        symbol = "ETH",
        name = "Ethereum",
        price = 170.0376093918,
        rank = 2,
        delta1h = -0.56,
        delta24h = -1.58,
        delta7d = 27.52,
        low24h = 163.3306438671,
        high24h = 174.8423532766,
        iconUrl = "https://assets.coinlayer.com/icons/ETH.png"
    )
)