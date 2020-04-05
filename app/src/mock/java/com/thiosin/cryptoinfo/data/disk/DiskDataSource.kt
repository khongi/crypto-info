package com.thiosin.cryptoinfo.data.disk

import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class DiskDataSource @Inject constructor() {

    fun getAllCoins(): List<DomainCoin> {
        return COINS
    }

    fun getAllCoinsBySymbol(symbol: String): List<DomainCoin> {
        return COINS.filter { it.symbol == symbol }
    }

    fun updateCoins(coins: List<DomainCoin>) {
        // no-op
    }

    fun updateCoin(coin: DomainCoin) {
        // no-op
    }

    fun getCoinBySymbol(symbol: String): DomainCoin {
        return COINS.first { it.symbol == symbol }
    }

    companion object {
        val COINS = listOf(
            DomainCoin(
                symbol = "BTC",
                name = "Bitcoin",
                price = 6088.72,
                rank = 1,
                delta1h = -1.1,
                delta24h = -2.2,
                delta7d = 3.3,
                low24h = 6075.03,
                high24h = 6267.01,
                iconUrl = "https://assets.coinlayer.com/icons/BTC.png"
            )
        )
    }

}