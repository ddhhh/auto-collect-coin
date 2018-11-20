package com.example.administrator.myapplication.http

import android.content.Context
import android.util.Log
import com.example.administrator.myapplication.BuildConfig
import com.example.administrator.myapplication.common.UserData
import com.example.administrator.myapplication.util.MD5Utils
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference


object LpClient {
    private const val BASE_URL = "http://newpai.xiaoxi6.com/"
    var context: WeakReference<Context>? = null
    private val cookieStore = HashMap<String, List<Cookie>>()
    fun createService(): ApiService {
        val builder = OkHttpClient.Builder()
        val interceptor = Interceptor {
            val originalRequest= it.request()
            val request: Request
            val httpBuilder = originalRequest.url()
                    .newBuilder()
            httpBuilder.addQueryParameter("_ver",BuildConfig.VERSION_NAME)//版本
            httpBuilder.addQueryParameter("_dtype", "Android")//设备分类
            httpBuilder.addQueryParameter("_t", (System.currentTimeMillis().toString() + "").substring(0, 10))//时间戳
            httpBuilder.addQueryParameter("_utoken", this.getToken())//token值
            httpBuilder.addQueryParameter("phone_model", android.os.Build.MODEL)//手机型号
            if (UserData.isLogin) {
                httpBuilder.addQueryParameter("user_id", UserData.getUserId())//token值
            }
            val serialId = MD5Utils.encode(android.os.Build.SERIAL)
            httpBuilder.addQueryParameter("uuid", serialId)
            request = originalRequest.newBuilder()
                    .url(httpBuilder.build())
                    .removeHeader("User-Agent").addHeader("User-Agent", "okhttp/3.8.0")
                    .build()
            it.proceed(request)
        }

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        builder.addInterceptor(interceptor)
        builder.cookieJar(object : CookieJar {
            override fun saveFromResponse(httpUrl: HttpUrl, list: List<Cookie>) {
                cookieStore[httpUrl.host()] = list
            }

            override fun loadForRequest(httpUrl: HttpUrl): List<Cookie> {
                val cookies = cookieStore[httpUrl.host()]
                return cookies ?: ArrayList()
            }
        })
        val retrofit: Retrofit =  Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit.create(ApiService::class.java)
    }

    /**
     * 获取token
     *
     * @return
     */
    private fun getToken(): String {

        return if (UserData.isLogin) {
            MD5Utils.encode(MD5Utils.encode("(^&$&^%IIOY(&*RTYR" + BuildConfig.VERSION_NAME +
                    "Android" + (System.currentTimeMillis().toString() + "").substring(0, 10) + "(^&$&^%IIOY(&*RTYR" + UserData.getUserId()))

        } else {
            //未登录
            MD5Utils.encode(MD5Utils.encode("(^&$&^%IIOY(&*RHG&*TYR" + BuildConfig.VERSION_NAME+
                    "Android" + (System.currentTimeMillis().toString() + "").substring(0, 10) + "(^&$&kj^%IIOY(&*&^RTYR"))
        }
    }
}