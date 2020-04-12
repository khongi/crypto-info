package com.thiosin.cryptoinfo.ui.details

import co.zsmb.rainbowcake.test.*
import co.zsmb.rainbowcake.test.base.ViewModelTest
import com.thiosin.cryptoinfo.common.BTC
import com.thiosin.cryptoinfo.common.PRESENTER_DETAILS_COIN
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsViewModelTest : ViewModelTest() {

    private val detailsPresenter: DetailsPresenter = mockk()

    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setUp() {
        clearMocks(detailsPresenter)
        detailsViewModel = DetailsViewModel(detailsPresenter)
    }

    @Test
    fun `load sets view state from cache`() = runBlocking {
        detailsViewModel.observeStateAndEvents { stateObserver, _ ->
            coEvery { detailsPresenter.getCachedCoin(BTC.symbol) } returns PRESENTER_DETAILS_COIN

            detailsViewModel.load(BTC.symbol)

            coVerify { detailsPresenter.getCachedCoin(BTC.symbol) }
            stateObserver.assertDidObserve(DetailsReady(PRESENTER_DETAILS_COIN))
        }
    }

    @Test
    fun `load returns if network call was not successful`() = runBlocking {
        detailsViewModel.observeStateAndEvents { stateObserver, _ ->
            coEvery { detailsPresenter.getCachedCoin(BTC.symbol) } returns PRESENTER_DETAILS_COIN
            coEvery { detailsPresenter.getNetworkCoin(BTC.symbol) } returns null

            detailsViewModel.load(BTC.symbol)

            coVerify { detailsPresenter.getNetworkCoin(BTC.symbol) }
            stateObserver.assertObservedLast(DetailsReady(PRESENTER_DETAILS_COIN))
        }
    }

    @Test
    fun `load sets view state from cache then from network`() = runBlocking {
        detailsViewModel.observeStateAndEvents { stateObserver, _ ->
            val cachedCoin = PRESENTER_DETAILS_COIN
            val networkCoin = PRESENTER_DETAILS_COIN.copy(price = "mock")
            coEvery { detailsPresenter.getCachedCoin(BTC.symbol) } returns cachedCoin
            coEvery { detailsPresenter.getNetworkCoin(BTC.symbol) } returns networkCoin

            detailsViewModel.load(BTC.symbol)

            coVerifyOrder {
                detailsPresenter.getCachedCoin(BTC.symbol)
                detailsPresenter.getNetworkCoin(BTC.symbol)
            }
            stateObserver.assertObserved(
                Loading, DetailsReady(cachedCoin), DetailsReady(networkCoin)
            )
        }
    }

    @Test
    fun `refresh returns if view state is not ready`() = runBlocking {
        detailsViewModel.observeStateAndEvents { stateObserver, _ ->
            stateObserver.reset()
            stateObserver.observed.add(Loading)

            detailsViewModel.refresh()

            stateObserver.assertDidObserve(Loading)
        }
    }

    @Test
    fun `refresh retains old state in case of network error`() = runBlocking {
        detailsViewModel.observeStateAndEvents { stateObserver, _ ->
            val cachedCoin = PRESENTER_DETAILS_COIN
            stateObserver.observed.add(DetailsReady(cachedCoin))
            coEvery { detailsPresenter.getNetworkCoin(BTC.symbol) } returns null

            detailsViewModel.refresh()

            coEvery { detailsPresenter.getNetworkCoin(BTC.symbol) }
            stateObserver.assertObservedLast(DetailsReady(cachedCoin))
        }
    }

}