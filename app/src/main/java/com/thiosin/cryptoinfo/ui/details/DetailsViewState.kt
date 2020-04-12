package com.thiosin.cryptoinfo.ui.details

import com.thiosin.cryptoinfo.ui.details.models.DetailsCoin

sealed class DetailsViewState

object Loading : DetailsViewState()

data class DetailsReady(val coin: DetailsCoin) : DetailsViewState()
