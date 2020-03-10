package com.thiosin.cryptoinfo

import android.os.Bundle
import co.zsmb.rainbowcake.navigation.SimpleNavActivity
import com.thiosin.cryptoinfo.ui.pager.PagerFragment

class MainActivity : SimpleNavActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.add(PagerFragment())
        }
    }

}