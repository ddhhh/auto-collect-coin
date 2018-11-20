package com.example.administrator.myapplication.skill

import android.util.Log
import com.example.administrator.myapplication.bean.Audio
import com.example.administrator.myapplication.bean.AudioBean
import com.example.administrator.myapplication.bean.AudioColumn
import com.example.administrator.myapplication.bean.Result
import com.example.administrator.myapplication.http.LpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AudioSkill {
    var audioCakeList: List<AudioColumn>? = null
    fun getAudioColumn() {
        LpClient.createService().getAudioColumn().enqueue(object : Callback<Result<List<AudioColumn>>> {
            override fun onFailure(call: Call<Result<List<AudioColumn>>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Result<List<AudioColumn>>>, response: Response<Result<List<AudioColumn>>>) {
                audioCakeList = response.body()?.data

                audioCakeList?.forEach {
                    getAudioList(it.audio_cate_id.toString(), "10")
                }

            }
        })
    }

    fun getAudioList(cakeId: String, page: String) {
        LpClient.createService().getAudioList(cakeId, page).enqueue(object : Callback<Result<AudioBean>> {
            override fun onFailure(call: Call<Result<AudioBean>>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<Result<AudioBean>>, response: Response<Result<AudioBean>>) {
                Log.e("tag", response.body()?.data?.audio_cate_id.toString())
                listen(response.body()?.data?.audio_cate_id.toString())
            }

        })
    }

    fun listen(id : String) {
        LpClient.createService().audioBuy(id).enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                t.printStackTrace()
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.e("tag", String(response.body()!!.bytes(), Charsets.UTF_8))
            }

        })
    }
}
