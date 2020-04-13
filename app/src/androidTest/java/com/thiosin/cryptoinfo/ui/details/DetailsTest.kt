package com.thiosin.cryptoinfo.ui.details

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thiosin.cryptoinfo.AppTestComponent
import com.thiosin.cryptoinfo.EspressoTest
import com.thiosin.cryptoinfo.MainActivity
import com.thiosin.cryptoinfo.robot.withRobot
import com.thiosin.cryptoinfo.ui.list.ListRobot
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsTest : EspressoTest<MainActivity>(
    MainActivity::class.java) {

    override fun injectDependencies(injector: AppTestComponent) {
        injector.inject(this)
    }

    @Before
    fun setUp() {
        withRobot<ListRobot>()
            .clickOnItem()
    }

    @Test
    fun should_NavigateBackToList_When_BackPressed() {
        withRobot<DetailsRobot>()
            .clickBack()
            .checkListPageIsVisible()
    }

}