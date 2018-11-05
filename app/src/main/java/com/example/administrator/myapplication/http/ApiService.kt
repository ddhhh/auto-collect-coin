package com.example.administrator.myapplication.http

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded



interface ApiService {
    /*登录注册*/
    //获取验证码
    @FormUrlEncoded
    @POST("index.php/lianpai/login/sendYzm")
    fun getYzm(@Field("user_mobile") user_mobile: String): Call<ResponseBody>

    //手机验证码登录
    @FormUrlEncoded
    @POST("index.php/lianpai/login/loginByMobile")
    fun phoneLogin(@Field("user_mobile") user_mobile: String,
                   @Field("yzm_code") yzm_code: String): Call<ResponseBody>


    //账号密码登录
    @FormUrlEncoded
    @POST("index.php/lianpai/login/loginByPass")
    fun accountLogin(@Field("user_name") user_name: String,
                     @Field("user_password") user_password: String): Call<ResponseBody>
}