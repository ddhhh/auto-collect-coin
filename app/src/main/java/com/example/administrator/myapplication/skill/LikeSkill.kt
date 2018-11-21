package com.example.administrator.myapplication.skill

import com.example.administrator.myapplication.http.LpClient
import org.json.JSONArray
import org.json.JSONObject

/**
 * 点赞
 */
class LikeSkill {

    fun getLikeList() {
        Thread(Runnable {
            val body = LpClient.createService().getHotList("8", "1").execute().body()
            body?.apply {
                val jsonObject = JSONObject(String(this.bytes(), Charsets.UTF_8))
                val jsonArray = jsonObject.get("data") as JSONArray
                for (i in 0 until jsonArray.length()) {
                    LpClient.createService().like("1", (jsonArray[i] as JSONObject).get("content_id").toString(), "", "3", "0").execute()
                }
            }
        }).start()
    }
}