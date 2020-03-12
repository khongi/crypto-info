package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsPresenter: DetailsPresenter
) : JobViewModel<DetailsViewState>(Loading) {

    fun load() = execute {
        // TODO get arguments from safeargs
        viewState = DetailsReady(detailsPresenter.getCoin(0))
    }

}
