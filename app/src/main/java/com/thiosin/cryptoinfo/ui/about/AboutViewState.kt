package com.thiosin.cryptoinfo.ui.about

sealed class AboutViewState

object Loading : AboutViewState()

data class AboutReady(val data: String = "") : AboutViewState()
