package com.thiosin.cryptoinfo.domain

import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.domain.models.GetCoinsDto

fun GetCoinsDto.toDomainCoin(): DomainCoin {
    return DomainCoin(
        symbol = symbol,
        name = name,
        price = price,
        rank = rank,
        delta24h = delta24h,
        iconUrl = iconUrl
    )
}
