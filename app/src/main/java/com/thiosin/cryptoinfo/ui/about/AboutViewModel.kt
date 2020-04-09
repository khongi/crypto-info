package com.thiosin.cryptoinfo.ui.about

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val aboutPresenter: AboutPresenter
) : RainbowCakeViewModel<AboutViewState>(AboutViewState)
