package com.thiosin.cryptoinfo.ui.about

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val aboutPresenter: AboutPresenter
) : JobViewModel<AboutViewState>(Loading) {

    fun load() = execute {
        viewState = AboutReady(aboutPresenter.getData())
    }

}
