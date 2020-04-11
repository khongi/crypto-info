package com.thiosin.cryptoinfo.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.thiosin.cryptoinfo.R
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.load()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph, binding.drawerLayout)
        binding.listToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        adapter = CoinAdapter()
        adapter.listener = this
        binding.coinsList.adapter = adapter

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }

        val searchItem = binding.listToolbar.menu.findItem(R.id.action_search)
        val searchView = (searchItem.actionView) as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true
            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.search(newText)
                return true
            }
        })
        searchItem.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                viewModel.clearSearch()
                return true
            }
        })
    }

    override fun render(viewState: ListViewState) {
        when (viewState) {
            Loading -> {
                Timber.d("Loading...")
                binding.refreshLayout.isRefreshing = true
            }
            is ListReady -> {
                Timber.d("Ready: $viewState")
                adapter.submitList(viewState.coins)
                binding.refreshLayout.isRefreshing = false
            }
        }
    }

    override fun onCoinClicked(coin: ListPresenter.ListCoin) {
        navigator.navigate(
            ListFragmentDirections.actionListFragmentToDetailsFragment(
                symbol = coin.symbol,
                title = coin.symbol
            )
        )
    }

}
