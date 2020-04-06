package com.thiosin.cryptoinfo.ui.util

import android.content.Context
import android.os.Build
import com.thiosin.cryptoinfo.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CommonValueFormatter @Inject constructor(
    private val context: Context
) {

    fun toDeltaColor(delta: Double): Int {
        val colorCode =
            if (delta >= 0) {
                R.color.colorPositive
            } else {
                R.color.colorNegative
            }

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.getColor(colorCode)
        } else {
            context.resources.getColor(colorCode)
        }
    }

}