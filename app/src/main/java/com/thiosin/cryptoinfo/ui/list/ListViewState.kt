package com.thiosin.cryptoinfo.ui.list

sealed class ListViewState

object Loading : ListViewState()

data class ListReady(val data: List<ListPresenter.ListCoin>) : ListViewState()
