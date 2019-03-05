package com.example.administrator.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.administrator.myapplication.bean.Result
import com.example.administrator.myapplication.bean.UserBean
import com.example.administrator.myapplication.common.UserData
import com.example.administrator.myapplication.http.LpClient
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.ref.WeakReference

//hsadsfasdf
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LpClient.context = WeakReference(this.applicationContext)
        tvGetCode.setOnClickListener {
            val phoneNum = edtPhone.text.toString()
            LpClient.createService().getYzm(phoneNum).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })
        }
        btn.setOnClickListener {
            val phoneNum = edtPhone.text.toString()
            LpClient.createService().accountLogin(phoneNum, edtCode.text.toString()).enqueue(object : Callback<Result<UserBean>> {
                override fun onFailure(call: Call<Result<UserBean>>, t: Throwable) {

                }

                override fun onResponse(call: Call<Result<UserBean>>, response: Response<Result<UserBean>>) {
                        UserData.isLogin = true
                        UserData.userBean = response.body()?.data
                        startActivity(Intent(this@MainActivity, TaskListActivity::class.java))
                }
            })
        }
    }

}
