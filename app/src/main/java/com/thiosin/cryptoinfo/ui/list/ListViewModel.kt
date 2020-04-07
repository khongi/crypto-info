package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.base.JobViewModel
import timber.log.Timber
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val listPresenter: ListPresenter
) : JobViewModel<ListViewState>(Loading) {

    fun load() = execute {
        viewState = ListReady(listPresenter.getCachedCoins())
        viewState = ListReady(listPresenter.getRefreshedCoins())
    }

    fun refresh() = execute {
        viewState = Loading
        viewState = ListReady(listPresenter.getRefreshedCoins())
    }

    fun search(query: String?) = executeNonBlocking {
        if (query == null) return@executeNonBlocking

        Timber.d("Searching for $query")
        viewState = ListReady(listPresenter.getCachedCoinsBySymbol(query))
    }

    fun clearSearch() = executeNonBlocking {
        Timber.d("Loading from cache")
        viewState = Loading
        viewState = ListReady(listPresenter.getCachedCoins())
    }

}
