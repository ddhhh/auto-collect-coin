package com.example.administrator.myapplication.bean

data class NewsInfo(
        val bottom_id: Int,
        val count: Int,
        val list: List<X>,
        val news: Int,
        val top_id: Int,
        val total: Int
)

data class X(
        val extra: Extra,
        val id: Int,
        val is_top: Any,
        val message_ad_style: Int,
        val order: Any,
        val short_title: Any,
        val title: String,
        val type: Int
)

data class Extra(
        val attribute_depth: String,
        val attribute_exclusive: String,
        val author: String,
        val author_level: Int,
        val published_at: Int,
        val read_number: Int,
        val source: Any,
        val summary: String,
        val thumbnail_pic: String,
        val topic_url: String,
        val version: String
)