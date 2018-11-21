package com.example.administrator.myapplication.skill

import com.example.administrator.myapplication.http.LpClient
import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReadNewsSkill {


    fun getMessageList() {
        Thread(Runnable {
           val body = LpClient.createService().getNews("1", "20", "").execute().body()
            body?.apply {
                val jsonObject = JSONObject(String(this.bytes(), Charsets.UTF_8))
                val jsonObject1 = jsonObject.get("data") as JSONObject
                val jsonArray = jsonObject1.get("list") as JSONArray

                for (i in 0 until jsonArray.length()) {
                    val jsonObject1 = jsonArray.getJSONObject(i)
                    val jsonObject2 = jsonObject1.get("extra") as JSONObject
                    val url =  jsonObject2.get("topic_url").toString()
                    LpClient.createService().readNews(url).execute()
                    if (i == 2) break
                }
            }
        }).start()
    }
}