package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.util.network.*
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val coinInteractor: CoinInteractor) {

    suspend fun getCoin(symbol: String): DetailsCoin = withIOContext {
        when (val response = coinInteractor.getCoin(symbol, true)) {
            is DataTransferSuccess -> response.result.toDetailsCoin()
            is NetworkUnavailableCached -> TODO()
            is NetworkErrorCached -> TODO()
            NetworkUnavailableNotCached -> TODO()
            NetworkErrorNotCached -> TODO()
        }
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

private fun DomainCoin.toDetailsCoin(): DetailsPresenter.DetailsCoin {
    return DetailsPresenter.DetailsCoin(
            symbol,
            name,
            price.toString(),
            rank.toString(),
            delta24h.toString(),
            iconUrl,
            low24h.toString(),
            high24h.toString(),
            delta1h.toString(),
            delta7d.toString()
    )
}
