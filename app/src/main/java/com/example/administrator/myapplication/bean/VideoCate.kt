package com.example.administrator.myapplication.bean

data class VideoCate(
        val cate_id: Int,
        val cate_img: String,
        val cate_info: String,
        val cate_name: String,
        val list: List<Video>
)

data class Video(
        val video_id: Int,
        val video_name: String
)