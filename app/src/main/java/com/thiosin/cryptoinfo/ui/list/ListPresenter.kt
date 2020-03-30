package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.util.network.*
import javax.inject.Inject

class ListPresenter @Inject constructor(private val coinInteractor: CoinInteractor) {

    suspend fun getCoins(): List<ListCoin> = withIOContext {
        when (val response = coinInteractor.getCoins(true)) {
            is DataTransferSuccess -> response.result.map(DomainCoin::toListCoin)
            is NetworkUnavailableCached -> TODO()
            is NetworkErrorCached -> TODO()
            NetworkUnavailableNotCached -> TODO()
            NetworkErrorNotCached -> TODO()
        }
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
