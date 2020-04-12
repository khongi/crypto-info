package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.test.assertObserved
import co.zsmb.rainbowcake.test.assertObservedLast
import co.zsmb.rainbowcake.test.base.ViewModelTest
import co.zsmb.rainbowcake.test.observeStateAndEvents
import com.thiosin.cryptoinfo.common.BTC
import com.thiosin.cryptoinfo.common.PRESENTER_LIST_COINS
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ListViewModelTest : ViewModelTest() {

    private val listPresenter: ListPresenter = mockk()

    private lateinit var listViewModel: ListViewModel

    @Before
    fun setUp() {
        clearMocks(listPresenter)
        listViewModel = ListViewModel(listPresenter)
    }

    @Test
    fun `load sets view state from cache then from network`() = runBlocking {
        listViewModel.observeStateAndEvents { stateObserver, _ ->
            val cachedCoins = PRESENTER_LIST_COINS.map { it.copy(price = "mock") }
            val refreshedCoins = PRESENTER_LIST_COINS
            coEvery { listPresenter.getCachedCoins() } returns cachedCoins
            coEvery { listPresenter.getRefreshedCoins() } returns refreshedCoins

            listViewModel.load()

            coVerifyOrder {
                listPresenter.getCachedCoins()
                listPresenter.getRefreshedCoins()
            }
            stateObserver.assertObserved(
                Loading, ListReady(cachedCoins), ListReady(refreshedCoins)
            )
        }
    }

    @Test
    fun `refresh returns refreshed coins`() = runBlocking {
        listViewModel.observeStateAndEvents { stateObserver, _ ->
            coEvery { listPresenter.getRefreshedCoins() } returns PRESENTER_LIST_COINS

            listViewModel.refresh()

            coVerify { listPresenter.getRefreshedCoins() }
            stateObserver.assertObserved(
                Loading, ListReady(PRESENTER_LIST_COINS)
            )
        }
    }

    @Test
    fun `search does not execute on null query`() = runBlocking {
        listViewModel.observeStateAndEvents { stateObserver, _ ->
            listViewModel.search(null)

            stateObserver.assertObservedLast(Loading)
        }
    }

    @Test
    fun `search does execute non null query`() = runBlocking {
        listViewModel.observeStateAndEvents { stateObserver, _ ->
            coEvery { listPresenter.getCachedCoinsBySymbol(any()) } returns PRESENTER_LIST_COINS

            listViewModel.search(BTC.symbol)

            coVerify { listPresenter.getCachedCoinsBySymbol(BTC.symbol) }
            stateObserver.assertObserved(
                Loading, ListReady(PRESENTER_LIST_COINS)
            )
        }
    }

    @Test
    fun `clearSearch sets view state to present all database coins`() = runBlocking {
        listViewModel.observeStateAndEvents { stateObserver, _ ->
            coEvery { listPresenter.getCachedCoins() } returns PRESENTER_LIST_COINS

            listViewModel.clearSearch()

            coVerify { listPresenter.getCachedCoins() }
            stateObserver.assertObserved(
                Loading, ListReady(PRESENTER_LIST_COINS)
            )
        }
    }

}