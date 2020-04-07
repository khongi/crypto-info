package com.thiosin.cryptoinfo.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.bumptech.glide.Glide
import com.thiosin.cryptoinfo.R
import com.thiosin.cryptoinfo.databinding.FragmentDetailsBinding
import com.thiosin.cryptoinfo.ui.util.NavFragment
import timber.log.Timber

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

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.detailsToolbar.setupWithNavController(navController, appBarConfiguration)

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
        }
    }

    override fun onStart() {
        super.onStart()

        viewModel.load(arguments!!.getString("symbol")!!)
    }

    override fun render(viewState: DetailsViewState) {
        when (viewState) {
            Loading -> {
                Timber.d("Loading...")
                binding.refreshLayout.isRefreshing = true
            }
            is DetailsReady -> {
                Timber.d(viewState.coin.toString())
                val coin = viewState.coin

                binding.nameText.text = coin.name
                binding.rankText.text = coin.rank
                binding.priceText.text = coin.price
                binding.low24hText.text = coin.low24h
                binding.high24hText.text = coin.high24h
                binding.delta24hText.text = coin.delta24h.value

                if (coin.delta1h != null) {
                    binding.delta1hText.text = coin.delta1h.value
                    binding.delta1hText.setTextColor(coin.delta1h.color)
                    Glide.with(this).load(coin.delta1h.image).into(binding.delta1hImage)
                } else {
                    binding.delta1hText.text = getText(R.string.not_available)
                }

                if (coin.delta7d != null) {
                    binding.delta7dText.text = coin.delta7d.value
                    binding.delta7dText.setTextColor(coin.delta7d.color)
                    Glide.with(this).load(coin.delta7d.image).into(binding.delta7dImage)
                } else {
                    binding.delta7dText.text = getText(R.string.not_available)
                }

                binding.delta24hText.text = coin.delta24h.value
                binding.delta24hText.setTextColor(coin.delta24h.color)
                Glide.with(this).load(coin.delta24h.image).into(binding.delta24hImage)

                Glide
                    .with(this)
                    .load(coin.iconUrl)
                    .placeholder(R.drawable.ic_coin)
                    .into(binding.coinImage)

                binding.refreshLayout.isRefreshing = false
            }
        }
    }

}
