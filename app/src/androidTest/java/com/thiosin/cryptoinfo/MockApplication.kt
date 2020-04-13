package com.thiosin.cryptoinfo

import com.thiosin.cryptoinfo.di.ApplicationModule

class MockApplication : CryptoInfoApplication() {

    override fun setupInjector() {
        injector = DaggerAppTestComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

}