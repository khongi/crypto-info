package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val coinInteractor: CoinInteractor) {

    suspend fun getCoin(id: Int): DetailsCoin = withIOContext {
        TODO()
    }

    data class DetailsCoin(val id: Int)

}

private fun DomainCoin.toDetailsCoin(): DetailsPresenter.DetailsCoin {
    TODO()
}
