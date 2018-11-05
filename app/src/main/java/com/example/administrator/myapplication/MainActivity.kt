package com.example.administrator.myapplication

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.administrator.myapplication.http.LpClient
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
            LpClient.createService().accountLogin(phoneNum, edtCode.text.toString()).enqueue(object : Callback<ResponseBody> {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                    Log.e("tag", String(response.body()!!.bytes(), Charsets.UTF_8))
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

                }

            })
        }
    }
}
