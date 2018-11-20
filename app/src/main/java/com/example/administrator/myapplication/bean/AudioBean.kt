package com.example.administrator.myapplication.bean

data class AudioBean(
        val audio_cate: List<Audio>,
        val audio_cate_id: Int,
        val audio_cate_name: String,
        val audio_cate_title: String,
        val audio_column_id: Int,
        val audio_column_info: String,
        val audio_column_name: String,
        val audio_column_picture: String,
        val audio_h5_url: String,
        val audio_num: Int,
        val content: String,
        val imageUrl: String,
        val subtitle_content: String,
        val title: String,
        val webPageUrl: String
)

data class Audio(
        val add_datetime: String,
        val audio_id: Int,
        val audio_info: String,
        val audio_info_url: String,
        val audio_name: String,
        val audio_time: Int,
        val audio_type: Int,
        val audio_url: String,
        val audio_yz_num: Int)