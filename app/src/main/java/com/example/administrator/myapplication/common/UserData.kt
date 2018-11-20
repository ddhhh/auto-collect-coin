package com.example.administrator.myapplication.common

import com.example.administrator.myapplication.bean.UserBean
import java.io.Serializable

object UserData : Serializable{
     var userBean: UserBean? = null
     var isLogin: Boolean = false

     fun getUserId(): String {
          return userBean?.user_id.toString()
     }
}
