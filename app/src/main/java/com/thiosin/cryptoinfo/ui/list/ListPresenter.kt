package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import javax.inject.Inject

class ListPresenter @Inject constructor(private val coinInteractor: CoinInteractor) {

    suspend fun getCoins(): List<ListCoin> = withIOContext {
        coinInteractor.getCoins().map(DomainCoin::toListCoin)
    }

    data class ListCoin(val id: Int)

}

private fun DomainCoin.toListCoin(): ListPresenter.ListCoin {
    return ListPresenter.ListCoin(this.id)
}
