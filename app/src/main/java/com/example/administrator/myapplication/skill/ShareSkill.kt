package com.example.administrator.myapplication.skill

import com.example.administrator.myapplication.http.LpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 分享
 */
class ShareSkill {

    /**
    @param type 传  5 听音频 6分享音频 7 阅读资讯 8 分享资讯 9邀请好友
    */
    fun execute(type: String, times: Int) {
        for (i in 0..times) {
            LpClient.createService().share(type).enqueue(object : Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                }

            })
        }
    }

    fun shareNews() {
        execute("8", 5)
    }

    fun listenMedia() {
        execute("5", 2)
    }

    fun readNews() {
        execute("7", 2)
    }

    fun invokeFriend() {

    }
}