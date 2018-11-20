package com.example.administrator.myapplication.skill

import com.example.administrator.myapplication.bean.Result
import com.example.administrator.myapplication.bean.VideoCate
import com.example.administrator.myapplication.http.LpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * 看视频
 */
class WatchVideoSkill {
    var videoList: List<VideoCate>? = null
    fun watch() {
        videoList?.forEach {
            it.list.forEach {video ->
                LpClient.createService().changeVideoParam(video.video_id.toString(), "video_show_num", "").enqueue(object : Callback<ResponseBody> {
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    }

                    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    }

                })
            }
        }

    }

    fun getVideoList() {
        LpClient.createService().getVideoCate("20", "1").enqueue(object : Callback<Result<List<VideoCate>>> {
            override fun onFailure(call: Call<Result<List<VideoCate>>>, t: Throwable) {
            }

            override fun onResponse(call: Call<Result<List<VideoCate>>>, response: Response<Result<List<VideoCate>>>) {
                videoList = response.body()?.data
                watch()
            }

        })
    }
}
