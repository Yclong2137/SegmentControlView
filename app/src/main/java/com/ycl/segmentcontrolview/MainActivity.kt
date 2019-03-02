package com.ycl.segmentcontrolview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        segment.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.rb_qq -> {
                    println("点击了QQ")
                }
                R.id.rb_wb -> {
                    println("点击了微博")
                }
                R.id.rb_wx -> {
                    println("点击了微信")
                }
                else -> {
                }
            }

        }
    }
}
