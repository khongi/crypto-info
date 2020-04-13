package com.thiosin.cryptoinfo.ui.list

import com.thiosin.cryptoinfo.EspressoTest
import com.thiosin.cryptoinfo.R
import com.thiosin.cryptoinfo.robot.ScreenRobot
import com.thiosin.cryptoinfo.robot.withRobot
import com.thiosin.cryptoinfo.ui.details.DetailsRobot

class ListRobot(espressoTest: EspressoTest<*>) : ScreenRobot<ListRobot>(espressoTest) {

    fun checkListItemCount(): ListRobot {
        return checkRecyclerViewItemCount(R.id.coinsList, 2)
    }

    fun checkListPageIsVisible(): ListRobot {
        return checkIsDisplayed(R.id.coinsList)
    }

    fun checkItemIsFilled(): ListRobot {
        return checkRecyclerViewItemHasText(R.id.coinsList, 0, "Bitcoin")
    }

    fun clickOnItem(): DetailsRobot {
        clickRecyclerViewItem(R.id.coinsList, 0)
        return espressoTest.withRobot()
    }

}