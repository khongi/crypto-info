package com.thiosin.cryptoinfo.ui.list

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class ListPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

    data class ListCoin(val id: Int)

}
