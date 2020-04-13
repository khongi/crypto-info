package com.thiosin.cryptoinfo.ui.list

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.thiosin.cryptoinfo.AppTestComponent
import com.thiosin.cryptoinfo.EspressoTest
import com.thiosin.cryptoinfo.MainActivity
import com.thiosin.cryptoinfo.robot.withRobot
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ListTest : EspressoTest<MainActivity>(MainActivity::class.java) {

    override fun injectDependencies(injector: AppTestComponent) {
        injector.inject(this)
    }

    @Test
    fun should_ShowListPage_When_ApplicationLaunches() {
        withRobot<ListRobot>()
            .checkListPageIsVisible()
    }

    @Test
    fun should_LoadListItems_When_ApplicationLaunches() {
        withRobot<ListRobot>()
            .checkListItemCount()
            .checkItemIsFilled()
    }

    @Test
    fun should_NavigateToDetails_When_ItemIsClicked() {
        withRobot<ListRobot>()
            .clickOnItem()
            .checkDetailsPageVisible()
    }

}