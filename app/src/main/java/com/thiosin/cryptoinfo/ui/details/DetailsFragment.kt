package com.thiosin.cryptoinfo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.thiosin.cryptoinfo.databinding.FragmentDetailsBinding
import com.thiosin.cryptoinfo.ui.util.NavFragment

class DetailsFragment : NavFragment<DetailsViewState, DetailsViewModel, FragmentDetailsBinding>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailsBinding {
        return FragmentDetailsBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: DetailsViewState) {
        // TODO Render state
    }

}
