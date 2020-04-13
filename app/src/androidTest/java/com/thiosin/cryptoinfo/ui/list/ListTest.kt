package com.thiosin.cryptoinfo.ui.list

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thiosin.cryptoinfo.AppTestComponent
import com.thiosin.cryptoinfo.EspressoTest
import com.thiosin.cryptoinfo.MainActivity
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ListTest : EspressoTest<MainActivity>(
    MainActivity::class.java) {

    override fun injectDependencies(injector: AppTestComponent) {
        injector.inject(this)
    }

}