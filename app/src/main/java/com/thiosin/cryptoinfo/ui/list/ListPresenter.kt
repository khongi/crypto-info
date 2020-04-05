package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class ListPresenter @Inject constructor(private val coinInteractor: CoinInteractor) {

    suspend fun getCoins(): List<ListCoin> = withIOContext {
        val coins = coinInteractor.getNetworkCoins()
        coins?.map(DomainCoin::toListCoin) ?: emptyList()
    }

    data class ListCoin(
        val symbol: String,
        val name: String,
        val price: String,
        val rank: String,
        val delta24h: String,
        val iconUrl: String
    )

}

private fun DomainCoin.toListCoin(): ListPresenter.ListCoin {
    // TODO formatting
    return ListPresenter.ListCoin(
        symbol = symbol,
        name = name,
        price = price.toString(),
        rank = rank.toString(),
        delta24h = delta24h.toString(),
        iconUrl = iconUrl
    )
}
