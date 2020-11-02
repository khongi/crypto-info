package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import timber.log.Timber
import javax.inject.Inject

class ListViewModel @Inject constructor(
    private val listPresenter: ListPresenter
) : RainbowCakeViewModel<ListViewState>(Loading) {

    fun load() = execute {
        Timber.d("Loading")
        viewState = ListReady(listPresenter.getCachedCoins())
        viewState = ListReady(listPresenter.getRefreshedCoins())
    }

    fun refresh() = execute {
        Timber.d("Refreshing")
        viewState = Loading
        viewState = ListReady(listPresenter.getRefreshedCoins())
    }

    fun search(query: String?) = executeNonBlocking {
        if (query == null) {
            Timber.d("Null query")
            return@executeNonBlocking
        }

        Timber.d("Searching for $query")
        viewState = ListReady(listPresenter.getCachedCoinsBySymbol(query))
    }

    fun clearSearch() = executeNonBlocking {
        Timber.d("Clearing Search")
        viewState = Loading
        viewState = ListReady(listPresenter.getCachedCoins())
    }

}
