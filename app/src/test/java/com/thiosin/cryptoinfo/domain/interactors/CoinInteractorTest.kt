package com.thiosin.cryptoinfo.domain.interactors

import com.google.common.truth.Truth.assertThat
import com.thiosin.cryptoinfo.domain.models.DomainCoin
import org.junit.Test

class CoinInteractorTest {

    private val coinInteractor = CoinInteractor()

    @Test
    fun `getCoins returns list with no duplicates`() {
        val list = coinInteractor.getCoins()

        assertThat(list).containsNoDuplicates()
    }

    @Test
    fun `getCoin returns the correct item`() {
        val item = coinInteractor.getCoin(1)

        assertThat(item).isEqualTo(DomainCoin(1))
    }

}