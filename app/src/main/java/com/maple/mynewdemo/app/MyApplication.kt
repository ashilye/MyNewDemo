package com.maple.mynewdemo.app

import android.app.Application
import android.graphics.Typeface
import es.dmoral.toasty.Toasty

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Toasty.Config.getInstance()
            .tintIcon(true) // optional (apply textColor also to the icon)
            .setToastTypeface(Typeface.DEFAULT) // optional
            .setTextSize(12) // optional
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply(); // required
    }
}