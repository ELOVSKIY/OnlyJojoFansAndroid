package com.helicoptera.onlyjojofans

import android.app.Application
import io.github.inflationx.viewpump.ViewPump

class App  : Application() {

    override fun onCreate() {
        super.onCreate()

        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(TextViewInterceptor())
                .build()
        )
    }
}