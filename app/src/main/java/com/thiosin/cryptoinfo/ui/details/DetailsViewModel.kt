package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsPresenter: DetailsPresenter
) : JobViewModel<DetailsViewState>(Loading) {

    fun load(symbol: String) = execute {
        viewState = DetailsReady(detailsPresenter.getCachedCoin(symbol))
        val refreshedCoin = detailsPresenter.getNetworkCoin(symbol) ?: return@execute
        viewState = DetailsReady(refreshedCoin)
    }

}
