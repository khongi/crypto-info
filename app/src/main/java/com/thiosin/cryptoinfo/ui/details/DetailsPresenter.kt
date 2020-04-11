package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.withIOContext
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import com.thiosin.cryptoinfo.ui.details.models.DetailsCoin
import com.thiosin.cryptoinfo.ui.details.models.DetailsCoinDelta
import com.thiosin.cryptoinfo.ui.util.CommonValueFormatter
import javax.inject.Inject

class DetailsPresenter @Inject constructor(
    private val coinInteractor: CoinInteractor,
    private val commonValueFormatter: CommonValueFormatter
) {

    suspend fun getCachedCoin(symbol: String): DetailsCoin = withIOContext {
        val coin = coinInteractor.getCachedCoin(symbol)
        coin.toDetailsCoin(commonValueFormatter)
    }

    suspend fun getNetworkCoin(symbol: String): DetailsCoin? = withIOContext {
        val coin = coinInteractor.getNetworkCoin(symbol) ?: return@withIOContext null
        coin.toDetailsCoin(commonValueFormatter)
    }

}

private fun DomainCoin.toDetailsCoin(formatter: CommonValueFormatter): DetailsCoin {
    val delta1h = if (this.delta1h != null) {
        DetailsCoinDelta(
            value = formatter.formatDelta(this.delta1h),
            color = formatter.toDeltaColor(this.delta1h),
            image = formatter.toDeltaImage(this.delta1h)
        )
    } else {
        null
    }
    val delta24h = DetailsCoinDelta(
        value = formatter.formatDelta(this.delta24h),
        color = formatter.toDeltaColor(this.delta24h),
        image = formatter.toDeltaImage(this.delta24h)
    )
    val delta7d = if (this.delta7d != null) {
        DetailsCoinDelta(
            value = formatter.formatDelta(this.delta7d),
            color = formatter.toDeltaColor(this.delta7d),
            image = formatter.toDeltaImage(this.delta7d)
        )
    } else {
        null
    }

    return DetailsCoin(
        symbol = symbol,
        name = name,
        price = formatter.formatPrice(price),
        rank = rank.toString(),
        iconUrl = iconUrl,
        low24h = formatter.formatPriceOrNotAvailable(low24h),
        high24h = formatter.formatPriceOrNotAvailable(high24h),
        delta1h = delta1h,
        delta24h = delta24h,
        delta7d = delta7d
    )
}
