package com.thiosin.cryptoinfo.ui.list

import com.thiosin.cryptoinfo.ui.list.models.ListCoin

sealed class ListViewState

object Loading : ListViewState()

data class ListReady(val coins: List<ListCoin>) : ListViewState()
