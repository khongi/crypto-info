package com.thiosin.cryptoinfo.ui.details

import androidx.test.espresso.Espresso
import com.thiosin.cryptoinfo.EspressoTest
import com.thiosin.cryptoinfo.R
import com.thiosin.cryptoinfo.robot.ScreenRobot
import com.thiosin.cryptoinfo.robot.withRobot
import com.thiosin.cryptoinfo.ui.list.ListRobot

class DetailsRobot(espressoTest: EspressoTest<*>) : ScreenRobot<DetailsRobot>(espressoTest) {

    fun checkDetailsPageVisible(): DetailsRobot {
        return checkIsDisplayed(R.id.detailsToolbar)
    }

    fun clickBack(): ListRobot {
        Espresso.pressBack()
        return espressoTest.withRobot()
    }

}