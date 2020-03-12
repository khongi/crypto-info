package com.thiosin.cryptoinfo.domain.interactors

import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class CoinInteractor @Inject constructor() {

    companion object {
        private val COINS = listOf(DomainCoin(0), DomainCoin(1))
    }

    fun getCoins(): List<DomainCoin> {
        return COINS
    }

    fun getCoin(id: Int): DomainCoin {
        return COINS.first { it.id == id }
    }

}