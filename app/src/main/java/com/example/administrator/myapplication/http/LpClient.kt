package com.example.administrator.myapplication.http

import com.example.administrator.myapplication.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.administrator.myapplication.common.UserData
import com.example.administrator.myapplication.util.Md5Util
import okhttp3.Request
import okhttp3.Interceptor
import android.util.Log
import okhttp3.logging.HttpLoggingInterceptor


object LpClient {
    private const val BASE_URL = "http://newpai.xiaoxi6.com/"


    fun createService(): ApiService {
        val builder = OkHttpClient.Builder()
        val interceptor = Interceptor {
            val originalRequest= it.request()
            val request: Request
            val httpBuilder = originalRequest.url().newBuilder()
            httpBuilder.addQueryParameter("_ver", BuildConfig.VERSION_NAME)//版本
            httpBuilder.addQueryParameter("_dtype", "Android")//设备分类
            httpBuilder.addQueryParameter("_t", (System.currentTimeMillis().toString() + "").substring(0, 10))//时间戳
            httpBuilder.addQueryParameter("_utoken", this.getToken())//token值
            httpBuilder.addQueryParameter("phone_model", android.os.Build.MODEL)//手机型号
            if (UserData.isLogin()) {
                httpBuilder.addQueryParameter("user_id", UserData.getUserId())//token值
            }
            val serialId = Md5Util.encode(android.os.Build.SERIAL)
            httpBuilder.addQueryParameter("uuid", serialId)
            request = originalRequest.newBuilder()
                    .url(httpBuilder.build()).build()
            it.proceed(request)
        }

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        builder.addInterceptor(logging)
        builder.addInterceptor(interceptor)
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

        return if (!UserData.isLogin()) {
            //未登录
            Md5Util.encode(Md5Util.encode("(^&$&^%IIOY(&*RHG&*TYR" +  BuildConfig.VERSION_NAME +
                    "Android" + (System.currentTimeMillis().toString() + "").substring(0, 10) + "(^&$&kj^%IIOY(&*&^RTYR"))
        } else {
            //登录
            Md5Util.encode(Md5Util.encode("(^&$&^%IIOY(&*RTYR" + BuildConfig.VERSION_NAME) +
                    "Android" + (System.currentTimeMillis().toString() + "").substring(0, 10) + "(^&$&^%IIOY(&*RTYR" + UserData.getUserId())
        }
    }
}