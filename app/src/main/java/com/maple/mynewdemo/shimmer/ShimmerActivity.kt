package com.maple.mynewdemo.shimmer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.facebook.shimmer.ShimmerFrameLayout
import com.maple.mynewdemo.R

class ShimmerActivity : AppCompatActivity() {

    private lateinit var shimmer: ShimmerFrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shimmer)
        shimmer = this.findViewById(R.id.shimmer)

        shimmer.startShimmer()

    }
}