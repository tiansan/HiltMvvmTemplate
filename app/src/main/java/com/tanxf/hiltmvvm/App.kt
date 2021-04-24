package com.tanxf.hiltmvvm

import android.app.Application
import com.dylanc.loadinghelper.LoadingHelper
import com.dylanc.loadinghelper.ViewType
import com.tanxf.hiltmvvm.ui.loading.EmptyAdapter
import com.tanxf.hiltmvvm.ui.loading.ErrorAdapter
import com.tanxf.hiltmvvm.ui.loading.LoadingAdapter
import com.yechaoa.yutilskt.LogUtil
import com.yechaoa.yutilskt.YUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initLoading()
        initYUtils()
    }

    private fun initYUtils() {
        YUtils.init(this);
        LogUtil.setIsLog(BuildConfig.DEBUG)
    }

    private fun initLoading() {
        LoadingHelper.setDefaultAdapterPool {
            register(ViewType.LOADING, LoadingAdapter())
            register(ViewType.ERROR, ErrorAdapter())
            register(ViewType.EMPTY, EmptyAdapter())
        }
    }

}