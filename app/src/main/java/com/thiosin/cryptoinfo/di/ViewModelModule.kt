package com.thiosin.cryptoinfo.di

import androidx.lifecycle.ViewModel
import co.zsmb.rainbowcake.dagger.ViewModelKey
import com.thiosin.cryptoinfo.ui.about.AboutViewModel
import com.thiosin.cryptoinfo.ui.details.DetailsViewModel
import com.thiosin.cryptoinfo.ui.list.ListViewModel
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

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(viewModel: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindDetailsViewModel(viewModel: DetailsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutViewModel::class)
    abstract fun bindAboutViewModel(viewModel: AboutViewModel): ViewModel

}