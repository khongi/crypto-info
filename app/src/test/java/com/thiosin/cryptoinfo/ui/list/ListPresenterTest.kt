package com.thiosin.cryptoinfo.ui.list

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.common.BTC
import com.thiosin.cryptoinfo.common.DOMAIN_COINS
import com.thiosin.cryptoinfo.common.ETH
import com.thiosin.cryptoinfo.common.PRESENTER_LIST_COINS
import com.thiosin.cryptoinfo.domain.interactors.CoinInteractor
import com.thiosin.cryptoinfo.ui.util.CommonValueFormatter
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class ListPresenterTest {

    private val coinInteractor: CoinInteractor = mockk()
    private val commonValueFormatter: CommonValueFormatter = mockk(relaxed = true)

    private val listPresenter = ListPresenter(coinInteractor, commonValueFormatter)

    @Before
    fun setUp() {
        clearMocks(coinInteractor)
        every { commonValueFormatter.formatPrice(BTC.price) } returns BTC.priceFormatted
        every { commonValueFormatter.formatPrice(ETH.price) } returns ETH.priceFormatted
        every { commonValueFormatter.formatDelta(BTC.delta24h) } returns BTC.delta24hFormatted
        every { commonValueFormatter.formatDelta(ETH.delta24h) } returns ETH.delta24hFormatted
        every { commonValueFormatter.toDeltaColor(BTC.delta24h) } returns BTC.delta24hColor
        every { commonValueFormatter.toDeltaColor(ETH.delta24h) } returns ETH.delta24hColor
    }

    @Test
    fun `getCachedCoins returns empty list if no coin is returned`() = runBlocking {
        every { coinInteractor.getCachedCoins() } returns null

        val coins = listPresenter.getCachedCoins()

        assertThat(coins).isEmpty()
    }

    @Test
    fun `getCachedCoins formats to list coin`() = runBlocking {
        every { coinInteractor.getCachedCoins() } returns DOMAIN_COINS

        val coins = listPresenter.getCachedCoins()

        assertThat(coins).containsExactlyElementsIn(PRESENTER_LIST_COINS).inOrder()
    }

    @Test
    fun `getRefreshedCoins returns empty list if no coin is returned`() = runBlocking {
        coEvery { coinInteractor.getNetworkCoins() } returns null

        val coins = listPresenter.getRefreshedCoins()

        assertThat(coins).isEmpty()
    }

    @Test
    fun `getRefreshedCoins formats to list coin`() = runBlocking {
        coEvery { coinInteractor.getNetworkCoins() } returns DOMAIN_COINS

        val coins = listPresenter.getRefreshedCoins()

        assertThat(coins).containsExactlyElementsIn(PRESENTER_LIST_COINS).inOrder()
    }

    @Test
    fun `getCachedCoinsBySymbol formats to list coin`() = runBlocking {
        coEvery { coinInteractor.getCachedCoinsBySymbol(BTC.symbol) } returns listOf(DOMAIN_COINS[0])

        val coins = listPresenter.getCachedCoinsBySymbol(BTC.symbol)

        assertThat(coins).contains(PRESENTER_LIST_COINS[0])
    }

}