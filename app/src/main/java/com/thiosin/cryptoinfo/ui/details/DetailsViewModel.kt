package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsPresenter: DetailsPresenter
) : JobViewModel<DetailsViewState>(Loading) {

    fun load() = execute {
        viewState = DetailsReady(detailsPresenter.getData())
    }

}
