package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val coinInteractor: CoinInteractor) {

    suspend fun getCoin(symbol: String): DetailsCoin = withIOContext {
        val coin = coinInteractor.getCoin(symbol)
        coin.toDetailsCoin()
    }

    data class DetailsCoin(
            val symbol: String,
            val name: String,
            val price: String,
            val rank: String,
            val delta24h: String,
            val iconUrl: String,
            val low24h: String,
            val high24h: String,
            val delta1h: String,
            val delta7d: String
    )

}

private fun DomainCoin?.toDetailsCoin(): DetailsPresenter.DetailsCoin {
    return DetailsPresenter.DetailsCoin(
        symbol = this?.symbol ?: "NA",
        name = this?.name ?: "NA",
        price = this?.price.toString(),
        rank = this?.rank.toString(),
        delta24h = this?.delta24h.toString(),
        iconUrl = this?.iconUrl ?: "NA",
        low24h = this?.low24h.toString(),
        high24h = this?.high24h.toString(),
        delta1h = this?.delta1h.toString(),
        delta7d = this?.delta7d.toString()
    )
}
