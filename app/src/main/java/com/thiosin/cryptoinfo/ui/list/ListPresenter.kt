package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.ui.list.models.ListCoin
import com.thiosin.cryptoinfo.ui.util.CommonValueFormatter
import javax.inject.Inject

class ListPresenter @Inject constructor(
    private val coinInteractor: CoinInteractor,
    private val commonValueFormatter: CommonValueFormatter
) {

    suspend fun getCachedCoins(): List<ListCoin> = withIOContext {
        val coins = coinInteractor.getCachedCoins()
        coins?.map { it.toListCoin(commonValueFormatter) } ?: emptyList()
    }

    suspend fun getRefreshedCoins(): List<ListCoin> = withIOContext {
        val coins = coinInteractor.getNetworkCoins()
        coins?.map { it.toListCoin(commonValueFormatter) } ?: emptyList()
    }

    suspend fun getCachedCoinsBySymbol(symbol: String): List<ListCoin> = withIOContext {
        val coins = coinInteractor.getCachedCoinsBySymbol(symbol)
        coins.map { it.toListCoin(commonValueFormatter) }
    }

}

private fun DomainCoin.toListCoin(formatter: CommonValueFormatter): ListCoin {
    return ListCoin(
        symbol = symbol,
        name = name,
        price = formatter.formatPrice(price),
        rank = rank.toString(),
        delta24h = formatter.formatDelta(delta24h),
        iconUrl = iconUrl,
        deltaTextColor = formatter.toDeltaColor(delta24h)
    )
}
