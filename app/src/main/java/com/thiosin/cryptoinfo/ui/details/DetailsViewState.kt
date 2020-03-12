package com.thiosin.cryptoinfo.ui.details

sealed class DetailsViewState

object Loading : DetailsViewState()

data class DetailsReady(val coin: DetailsPresenter.DetailsCoin) : DetailsViewState()
