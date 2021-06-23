package com.maple.mynewdemo

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.maple.mynewdemo.snackbar.AnimatedSnackbar
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.findViewById<Button>(R.id.btn_demo1).setOnClickListener {
            Toasty.normal(this, "normal!", Toast.LENGTH_SHORT).show();
        }


        this.findViewById<Button>(R.id.btn_demo2).setOnClickListener {
            showSnackbar()
        }
    }

    private fun showSnackbar() {
        AnimatedSnackbar(this)
            .setMessage("测试")
            .show()

    }
}