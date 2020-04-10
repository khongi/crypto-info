package com.thiosin.cryptoinfo.domain.interactors

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.common.BTC
import com.thiosin.cryptoinfo.common.DOMAIN_COINS
import com.thiosin.cryptoinfo.common.DOMAIN_GETCOINDTO
import com.thiosin.cryptoinfo.common.DOMAIN_GETCOINSDTO_LIST
import com.thiosin.cryptoinfo.data.disk.DiskDataSource
import com.thiosin.cryptoinfo.data.network.NetworkDataSource
import com.thiosin.cryptoinfo.util.network.NetworkResult
import com.thiosin.cryptoinfo.util.network.NetworkUnavailable
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CoinInteractorTest {

    private val networkDataSource: NetworkDataSource = mockk()
    private val diskDataSource: DiskDataSource = mockk()

    private val coinInteractor = CoinInteractor(
        networkDataSource = networkDataSource,
        diskDataSource = diskDataSource
    )

    @Before
    fun setupUp() {
        clearAllMocks()
    }

    @Test
    fun `getCachedCoins does not access network`() = runBlocking {
        every { diskDataSource.getAllCoins() } returns DOMAIN_COINS

        coinInteractor.getCachedCoins()

        coVerify(exactly = 0) { networkDataSource.getCoinList() }
    }

    @Test
    fun `getCachedCoins retrieves coins from database`() = runBlocking {
        every { diskDataSource.getAllCoins() } returns DOMAIN_COINS

        val coins = coinInteractor.getCachedCoins()

        assertThat(coins).isNotNull()
        assertThat(coins).containsExactlyElementsIn(DOMAIN_COINS).inOrder()
        verify { diskDataSource.getAllCoins() }
    }

    @Test
    fun `getCachedCoins returns null if exception occurs`() = runBlocking {
        every { diskDataSource.getAllCoins() } throws Throwable()

        val coins = coinInteractor.getCachedCoins()

        assertThat(coins).isNull()
    }

    @Test
    fun `getCachedCoinsBySymbol returns empty list if exception occurs`() = runBlocking {
        every { diskDataSource.getAllCoinsBySymbol(any()) } throws Throwable()

        val coins = coinInteractor.getCachedCoinsBySymbol(BTC.symbol)

        assertThat(coins).isEmpty()
    }

    @Test
    fun `getCachedCoinsBySymbol returns correct list`() = runBlocking {
        every { diskDataSource.getAllCoinsBySymbol(BTC.symbol) } returns listOf(DOMAIN_COINS[0])

        val coins = coinInteractor.getCachedCoinsBySymbol(BTC.symbol)

        assertThat(coins).containsExactly(DOMAIN_COINS[0]).inOrder()
    }

    @Test
    fun `getNetworkCoins returns coins from database`() = runBlocking {
        coEvery { networkDataSource.getCoinList() } returns NetworkResult(DOMAIN_GETCOINSDTO_LIST)
        every { diskDataSource.updateCoins(any()) } returns Unit
        every { diskDataSource.getAllCoins() } returns DOMAIN_COINS

        coinInteractor.getNetworkCoins()

        coVerifyOrder {
            networkDataSource.getCoinList()
            diskDataSource.updateCoins(any())
            coinInteractor.getCachedCoins()
        }
    }

    @Test
    fun `getNetworkCoins returns null in case of error`() = runBlocking {
        coEvery { networkDataSource.getCoinList() } returns NetworkUnavailable

        val coins = coinInteractor.getNetworkCoins()

        assertThat(coins).isNull()
    }

    @Test(expected = IllegalStateException::class)
    fun `getCachedCoin throws exception if coin not found`() = runBlocking {
        every { diskDataSource.getCoinBySymbol(any()) } throws Throwable()

        coinInteractor.getCachedCoin(BTC.symbol)
        Unit
    }

    @Test
    fun `getCachedCoin returns correct coin`() = runBlocking {
        every { diskDataSource.getCoinBySymbol(BTC.symbol) } returns DOMAIN_COINS[0]

        val coin = coinInteractor.getCachedCoin(BTC.symbol)

        verify { diskDataSource.getCoinBySymbol(BTC.symbol) }
        assertThat(coin).isEqualTo(DOMAIN_COINS[0])
    }

    @Test
    fun `getNetworkCoin updates the coin in database before returning from database`() =
        runBlocking {
            coEvery { networkDataSource.getCoin(BTC.symbol) } returns NetworkResult(
                DOMAIN_GETCOINDTO
            )
            every { diskDataSource.updateCoin(any()) } returns Unit
            every { diskDataSource.getCoinBySymbol(BTC.symbol) } returns DOMAIN_COINS[0]

            val coin = coinInteractor.getNetworkCoin(BTC.symbol)

            coVerifyOrder {
                networkDataSource.getCoin(BTC.symbol)
                diskDataSource.updateCoin(any())
                coinInteractor.getCachedCoin(BTC.symbol)
            }
            assertThat(coin).isEqualTo(DOMAIN_COINS[0])
        }

    @Test
    fun `getNetworkCoin returns null in case of error`() = runBlocking {
        coEvery { networkDataSource.getCoin(BTC.symbol) } returns NetworkUnavailable

        val coin = coinInteractor.getNetworkCoin(BTC.symbol)

        assertThat(coin).isNull()
    }

}