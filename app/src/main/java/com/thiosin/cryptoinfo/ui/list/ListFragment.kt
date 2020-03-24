package com.thiosin.cryptoinfo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.thiosin.cryptoinfo.databinding.FragmentListBinding
import com.thiosin.cryptoinfo.ui.util.NavFragment

class ListFragment : NavFragment<ListViewState, ListViewModel, FragmentListBinding>() {

    override fun provideViewModel() = getViewModelFromFactory()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailsButton.setOnClickListener {
            val directions = ListFragmentDirections.actionListFragmentToDetailsFragment("dummyId")
            navigator.navigate(directions)
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: ListViewState) {
        // TODO Render state
    }

}
