package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsPresenter: DetailsPresenter
) : RainbowCakeViewModel<DetailsViewState>(Loading) {

    fun load(symbol: String) = execute {
        viewState = DetailsReady(detailsPresenter.getCachedCoin(symbol))
        val refreshedCoin = detailsPresenter.getNetworkCoin(symbol) ?: return@execute
        viewState = DetailsReady(refreshedCoin)
    }

    fun refresh() = execute {
        val oldState = (state.value as? DetailsReady) ?: return@execute
        val symbol = oldState.coin.symbol

        viewState = Loading

        val refreshedCoin = detailsPresenter.getNetworkCoin(symbol)
        if (refreshedCoin == null) {
            viewState = oldState
            return@execute
        }

        viewState = DetailsReady(refreshedCoin)
    }

}
