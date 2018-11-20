package com.example.administrator.myapplication.bean

data class Result<T: Any>(var code: Int,
                     var msg: String,
                     var show: Int,
                     var data: T)
