package com.example.administrator.myapplication.skill

import com.example.administrator.myapplication.common.UserData
import com.example.administrator.myapplication.http.LpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 签到
 */
class SignInSkill {

     fun execute() {

        LpClient.createService().sign(UserData.getUserId()).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

            }

        })
    }
}
