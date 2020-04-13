package com.thiosin.cryptoinfo

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class ApplicationTestRunner : AndroidJUnitRunner() {

    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, MockApplication::class.java.name, context)
    }

}