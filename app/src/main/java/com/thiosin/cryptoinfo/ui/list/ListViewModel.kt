package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.base.JobViewModel
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val listPresenter: ListPresenter
) : JobViewModel<ListViewState>(Loading) {

    fun load() = execute {
        viewState = ListReady(listPresenter.getCoins())
    }

}
