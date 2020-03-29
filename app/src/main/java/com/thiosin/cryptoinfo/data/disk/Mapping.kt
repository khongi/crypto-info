package com.thiosin.cryptoinfo.data.disk

import com.thiosin.cryptoinfo.data.disk.models.RoomCoin
import com.thiosin.cryptoinfo.domain.models.DomainCoin

fun RoomCoin.toDomainCoin(): DomainCoin {
    return DomainCoin(
        id = id,
        symbol = symbol,
        name = name,
        price = price,
        rank = rank,
        delta24h = delta24h,
        iconUrl = iconUrl,
        low24h = low24h,
        high24h = high24h,
        delta1h = delta1h,
        delta7d = delta7d
    )
}

fun DomainCoin.toRoomCoin(): RoomCoin {
    return RoomCoin(
        id = id,
        symbol = symbol,
        name = name,
        price = price,
        rank = rank,
        delta24h = delta24h,
        iconUrl = iconUrl,
        low24h = low24h,
        high24h = high24h,
        delta1h = delta1h,
        delta7d = delta7d
    )
}