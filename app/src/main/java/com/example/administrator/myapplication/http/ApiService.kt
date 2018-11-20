package com.example.administrator.myapplication.http

import com.example.administrator.myapplication.bean.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET


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
                     @Field("user_password") user_password: String): Call<Result<UserBean>>


    //分享、收藏、点赞(社区)
    @FormUrlEncoded
    @POST("lianpai/user/shareCallback")
    fun share(@Field("type") type: String): Call<ResponseBody>


    //点击签到接口
    @FormUrlEncoded
    @POST("lianpai/Column/userSign")
    fun sign(@Field("user_id") user_id: String): Call<ResponseBody>

    //社群分享成功回调
    @FormUrlEncoded
    @POST("/lianpai/rush/share_notify")
     fun notifyShare(@Field("user_id") userId: String): Call<ResponseBody>

    //视频参数修改
    @FormUrlEncoded
    @POST("lianpai/video/video_param")
    fun changeVideoParam(@Field("id") id: String,
                                 @Field("word") word: String,
                                 @Field("share_class") show_time: String): Call<ResponseBody>

    //首页视频 专栏、百科
    @FormUrlEncoded
    @POST("lianpai/column/video_category2_1")
    fun getVideoCate(@Field("video_cate_id") video_cate_id: String, @Field("page") page: String): Call<Result<List<VideoCate>>>

    @FormUrlEncoded
    @POST("lianpai/column/Get_videoList")
    fun getViedoList(@Field("cate_id") id: String, @Field("page") page: String, @Field("limit") limit: String)

    //音频栏目列表
    @GET("lianpai/column/audio_column")
    fun getAudioColumn(): Call<Result<List<AudioColumn>>>

    @FormUrlEncoded
    @POST("lianpai/audio/audioCateList")
    fun getAudioList(@Field("audio_cate_id")  id: String, @Field("page") page: String): Call<Result<AudioBean>>
    //音频购买
    @FormUrlEncoded
    @POST("index.php/lianpai/audio/upAudioShowNum")
    fun audioBuy(@Field("audio_id")audio_id: String ): Call<ResponseBody>


    @FormUrlEncoded
    @POST("lianpai/message/msessage_index")
    fun getNews(@Field("type") type: String, @Field("limit") limit: String, @Field("information_id") information_id: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("lianpai/coin/coin_hot")
    fun getHotList(@Field("limit") limit: String, @Field("page") page: String): Call<ResponseBody>

    @FormUrlEncoded
    @POST("lianpai/post/share_success")
    fun like(@Field("content_type") content_type: String, @Field("content_id") id: String,
             @Field("post_comment_id") post_id: String, @Field("type") type: String, @Field("share_class") clazz: String): Call<ResponseBody>
}