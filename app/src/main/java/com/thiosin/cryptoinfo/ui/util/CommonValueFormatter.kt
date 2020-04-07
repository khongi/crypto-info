package com.thiosin.cryptoinfo.ui.util

import android.content.Context
import androidx.core.content.ContextCompat
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

        return ContextCompat.getColor(context, colorCode)
    }

    fun toDeltaImage(delta: Double): Int {
        return if (delta >= 0) {
            R.drawable.ic_up
        } else {
            R.drawable.ic_down
        }
    }

    fun formatDelta(delta: Double): String {
        return context.getString(R.string.format_delta, delta)
    }

    fun formatPrice(price: Double): String {
        return context.getString(R.string.format_price, price)
    }

    fun formatPriceOrNotAvailable(value: Double?): String {
        if (value == null) {
            return context.getString(R.string.not_available)
        }
        return formatPrice(value)
    }

}