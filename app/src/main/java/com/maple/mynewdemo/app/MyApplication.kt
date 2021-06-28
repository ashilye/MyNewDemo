package com.maple.mynewdemo.app

import android.app.Application
import android.graphics.Typeface
import com.maple.mynewdemo.R
import com.maple.mynewdemo.refresh.MyRefreshFooter
import com.maple.mynewdemo.refresh.MyRefreshHeader
import com.scwang.smart.refresh.layout.SmartRefreshLayout

import com.tencent.mmkv.MMKV
import es.dmoral.toasty.Toasty

class MyApplication: Application() {

    companion object {
        @JvmStatic
        lateinit var instance: MyApplication
            private set
    }

    init {
        SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, layout ->
            layout.setPrimaryColorsId(R.color.white, R.color.theme)
//            ClassicsHeader(context).setDrawableArrowSize(14f).setDrawableProgressSize(14f).setTextSizeTitle(14f).setTextSizeTime(10f)
            MyRefreshHeader(context)
        }

        SmartRefreshLayout.setDefaultRefreshFooterCreator { context, layout ->
//            ClassicsFooter(context).setDrawableSize(14f).setTextSizeTitle(14f).setAccentColor(
//            UIUtils.getColor(R.color.theme))
            MyRefreshFooter(context)
        }
    }
    override fun onCreate() {
        super.onCreate()
        Toasty.Config.getInstance()
            .tintIcon(true) // optional (apply textColor also to the icon)
            .setToastTypeface(Typeface.DEFAULT) // optional
            .setTextSize(12) // optional
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply(); // required

        MMKV.initialize(this)
    }
}