package com.thiosin.cryptoinfo.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.thiosin.cryptoinfo.ui.pager.PagerViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PagerViewModel::class)
    abstract fun bindPagerViewModel(viewModel: PagerViewModel): ViewModel

}