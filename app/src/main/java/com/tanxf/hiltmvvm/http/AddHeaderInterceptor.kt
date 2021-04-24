package com.tanxf.hiltmvvm.http

import com.tanxf.hiltmvvm.common.PrefsConstants
import com.yechaoa.yutilskt.LogUtil
import com.yechaoa.yutilskt.SpUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

/**
 * Created by yechao on 2020/3/10/010.
 * Describe : 从响应头里拿到cookie并存起来，后面的每次请求再添加到请求头里
 */
class AddHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val stringSet = SpUtil.getStringSet(PrefsConstants.COOKIE)
        for (cookie in stringSet) {
            builder.addHeader("Cookie", cookie)
            LogUtil.i("Adding Header: $cookie")
        }
        return chain.proceed(builder.build())
    }
}