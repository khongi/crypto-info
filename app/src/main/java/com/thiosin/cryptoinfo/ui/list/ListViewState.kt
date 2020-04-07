package com.thiosin.cryptoinfo.ui.list

sealed class ListViewState

object Loading : ListViewState()

data class ListReady(val coins: List<ListPresenter.ListCoin>) : ListViewState()
