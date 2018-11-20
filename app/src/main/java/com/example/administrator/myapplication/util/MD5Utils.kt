package com.example.administrator.myapplication.util

import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Mr_yang on 2016/11/4.
 */

object MD5Utils {
    // 进行md5的加密运算
    fun encode(password: String): String? {
        // MessageDigest专门用于加密的类
        try {
            val messageDigest = MessageDigest.getInstance("MD5")
            val result = messageDigest.digest(password.toByteArray()) // 得到加密后的字符组数

            val sb = StringBuffer()

            for (b in result) {
                val num = b and 0xff // 这里的是为了将原本是byte型的数向上提升为int型，从而使得原本的负数转为了正数
                val hex = Integer.toHexString(num) //这里将int型的数直接转换成16进制表示
                //16进制可能是为1的长度，这种情况下，需要在前面补0，
                if (hex.length == 1) {
                    sb.append(0)
                }
                sb.append(hex)
            }

            return sb.toString()

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
            return null
        }

    }


    //写一个md5加密的方法
    fun md5(plainText: String): String {
        //定义一个字节数组
        var secretBytes: ByteArray? = null
        try {
            // 生成一个MD5加密计算摘要
            val md = MessageDigest.getInstance("MD5")
            //对字符串进行加密
            md.update(plainText.toByteArray())
            //获得加密后的数据
            secretBytes = md.digest()
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException("没有md5这个算法！")
        }

        //将加密后的数据转换为16进制数字
        var md5code = BigInteger(1, secretBytes).toString(16)// 16进制数字
        // 如果生成数字未满32位，需要前面补0
        for (i in 0 until 32 - md5code.length) {
            md5code = "0$md5code"
        }
        return md5code
    }
}
