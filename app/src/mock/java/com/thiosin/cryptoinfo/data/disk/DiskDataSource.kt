package com.thiosin.cryptoinfo.data.disk

import com.thiosin.cryptoinfo.data.common.DOMAIN_COINS
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
class DiskDataSource @Inject constructor() {

    fun getAllCoins(): List<DomainCoin> {
        return DOMAIN_COINS
    }

    fun getAllCoinsBySymbol(symbol: String): List<DomainCoin> {
        return DOMAIN_COINS.filter { it.symbol == symbol }
    }

    fun updateCoins(coins: List<DomainCoin>) {
        // no-op
    }

    fun updateCoin(coin: DomainCoin) {
        // no-op
    }

    fun getCoinBySymbol(symbol: String): DomainCoin {
        return DOMAIN_COINS.first { it.symbol == symbol }
    }

}