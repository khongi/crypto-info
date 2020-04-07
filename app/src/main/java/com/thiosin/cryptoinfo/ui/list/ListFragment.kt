package com.thiosin.cryptoinfo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.thiosin.cryptoinfo.databinding.FragmentListBinding
import com.thiosin.cryptoinfo.ui.util.NavFragment
import timber.log.Timber

class ListFragment : NavFragment<ListViewState, ListViewModel, FragmentListBinding>(),
    CoinAdapter.Listener {

    private lateinit var adapter: CoinAdapter

    override fun provideViewModel() = getViewModelFromFactory()

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentListBinding {
        return FragmentListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CoinAdapter()
        adapter.listener = this
        binding.coinsList.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: ListViewState) {
        when (viewState) {
            Loading -> {
                Timber.d("Loading...")
                binding.refreshLayout.isRefreshing = true
            }
            is ListReady -> {
                Timber.d(viewState.coins.toString())
                adapter.submitList(viewState.coins)
                binding.refreshLayout.isRefreshing = false
            }
        }
    }

    override fun onCoinClicked(coin: ListPresenter.ListCoin) {
        navigator.navigate(
            ListFragmentDirections.actionListFragmentToDetailsFragment(
                symbol = coin.symbol,
                title = coin.name
            )
        )
    }

}
