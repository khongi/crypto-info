package com.thiosin.cryptoinfo.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.thiosin.cryptoinfo.databinding.FragmentAboutBinding
import com.thiosin.cryptoinfo.ui.util.NavFragment

class AboutFragment : NavFragment<AboutViewState, AboutViewModel, FragmentAboutBinding>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAboutBinding {
        return FragmentAboutBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: AboutViewState) {
        // TODO Render state
    }

}