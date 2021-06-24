package com.maple.mynewdemo

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.maple.mynewdemo.snackbar.AnimatedSnackbar
import com.maple.mynewdemo.user.UserInfo
import com.maple.mynewdemo.utils.LogUtils
import com.tencent.mmkv.MMKV
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {
    private lateinit var tvInfo: TextView

    private val kv: MMKV? by lazy { MMKV.defaultMMKV() }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInfo = this.findViewById(R.id.tv_info)

        this.findViewById<Button>(R.id.btn_demo1).setOnClickListener {
            Toasty.normal(this, "normal!", Toast.LENGTH_SHORT).show();
        }

        this.findViewById<Button>(R.id.btn_demo2).setOnClickListener {
            showSnackbar()
        }

        this.findViewById<Button>(R.id.btn_demo3).setOnClickListener {
            saveUser()
        }

        this.findViewById<Button>(R.id.btn_demo4).setOnClickListener {
            showUser()
        }
    }

    private fun saveUser() {

        val list: MutableList<String> = mutableListOf()

        for (index in 1..100) {
            list.add(index.toString())
        }

        val userInfo: UserInfo = UserInfo("测试姓名",18 ,"https://avatars.githubusercontent.com/u/12546980?v=4",list)
        val resultUser: Boolean = kv?.encode("userInfo", userInfo.toString())?: false
        val resultFirst = kv?.encode("isFirst", true)

    }

    private fun showUser() {
        kv?.let { kv ->
            val userInfo: String? = kv.getString("userInfo","")
            val isFirst: Boolean = kv.getBoolean("isFirst",false)
            userInfo?.let {  userInfo ->
                tvInfo.text = userInfo
            }

            LogUtils.logGGQ("---isFirst--->:${isFirst}")
        }


    }

    private fun showSnackbar() {
        AnimatedSnackbar(this)
            .setMessage("测试")
            .show()
    }


}