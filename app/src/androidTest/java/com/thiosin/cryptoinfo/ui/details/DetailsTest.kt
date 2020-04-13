package com.thiosin.cryptoinfo.ui.details

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thiosin.cryptoinfo.AppTestComponent
import com.thiosin.cryptoinfo.EspressoTest
import com.thiosin.cryptoinfo.MainActivity
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsTest : EspressoTest<MainActivity>(
    MainActivity::class.java) {

    override fun injectDependencies(injector: AppTestComponent) {
        injector.inject(this)
    }

}