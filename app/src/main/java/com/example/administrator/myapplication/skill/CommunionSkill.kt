package com.example.administrator.myapplication.skill

import android.util.Log
import com.example.administrator.myapplication.common.UserData
import com.example.administrator.myapplication.http.LpClient
import com.google.gson.JsonObject
import org.json.JSONArray
import org.json.JSONObject

class CommunionSkill {
    fun getHotList() {
        Thread(Runnable {
            val body = LpClient.createService().getCoinHotList("8", "1").execute().body()
            body?.apply {
                val jsonObject = JSONObject(String(this.bytes(), Charsets.UTF_8))
                val datas = jsonObject.get("data") as JSONArray
                for (i in 0 until datas.length() - 1) {
                    val item  = datas[i] as JSONObject
                    val content_type = item.get("content_type").toString()
                    val user_id = item.get("user_id").toString()
                    val content_id = item.get("content_id").toString()
                    LpClient.createService().addComment(content_type, content_id, "666", "0", UserData.getUserId()).execute()
                }


            }
        }).start()
    }
}
