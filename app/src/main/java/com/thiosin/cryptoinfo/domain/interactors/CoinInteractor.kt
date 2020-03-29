package com.thiosin.cryptoinfo.domain.interactors

import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class CoinInteractor @Inject constructor() {

    fun getCoins(): List<DomainCoin> {
        return listOf()
    }

    fun getCoin(id: Int): DomainCoin {
        TODO()
    }

}