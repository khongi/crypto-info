package com.thiosin.cryptoinfo.ui.about

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class AboutPresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
