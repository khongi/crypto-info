package com.thiosin.cryptoinfo.ui.pager

import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.thiosin.cryptoinfo.R

class PagerFragment : RainbowCakeFragment<PagerViewState, PagerViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_pager

    override fun render(viewState: PagerViewState) = Unit

}