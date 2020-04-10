package com.thiosin.cryptoinfo.ui.details

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.common.BTC
import com.thiosin.cryptoinfo.common.DOMAIN_COINS
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.ui.util.CommonValueFormatter
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class DetailsPresenterTest {

    private val coinInteractor: CoinInteractor = mockk()
    private val commonValueFormatter: CommonValueFormatter = mockk(relaxed = true)

    private val detailsPresenter = DetailsPresenter(coinInteractor, commonValueFormatter)

    @Before
    fun setUp() {
        clearMocks(coinInteractor)
    }

    @Test
    fun `getCachedCoin does not access network`() = runBlocking {
        every { coinInteractor.getCachedCoin(BTC.symbol) } returns DOMAIN_COINS[0]

        detailsPresenter.getCachedCoin(BTC.symbol)

        coVerify(exactly = 0) { coinInteractor.getNetworkCoin(any()) }
    }

    @Test
    fun `getNetworkCoin returns null if in case of error`() = runBlocking {
        coEvery { coinInteractor.getNetworkCoin(BTC.symbol) } returns null

        val coin = detailsPresenter.getNetworkCoin(BTC.symbol)

        assertThat(coin).isNull()
    }

    @Test
    fun `getNetworkCoin retrieves coin from network`() = runBlocking {
        coEvery { coinInteractor.getNetworkCoin(BTC.symbol) } returns DOMAIN_COINS[0]

        detailsPresenter.getNetworkCoin(BTC.symbol)

        coVerify { coinInteractor.getNetworkCoin(BTC.symbol) }
    }
}