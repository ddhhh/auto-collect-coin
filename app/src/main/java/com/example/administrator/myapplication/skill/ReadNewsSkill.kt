package com.example.administrator.myapplication.skill

import android.util.Log
import com.example.administrator.myapplication.http.LpClient
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadNewsSkill {


    fun getMessageList() {
            LpClient.createService().getNews("1", "20", "").enqueue(object :Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    val jsonObject = JSONObject(String(response.body()!!.bytes(), Charsets.UTF_8))
                    val jsonObject1 = jsonObject.get("data") as JSONObject
                    val jsonArray = jsonObject1.get("list") as JSONArray

                    for (i in 0 until jsonArray.length()) {
                        val j = jsonArray.getJSONObject(i)
                        Log.e("tag", j.get("id").toString())
                    }
                }

            })
    }
}