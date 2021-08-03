package com.mad.zocket.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.mad.zocket.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = mBinding.root
        setContentView(view)
        // post delay 3ms
        Handler(Looper.getMainLooper()).postDelayed({
            moveToMainActivity()
        }, 3000)
    }

    // move to next activity after splash animation
    private fun moveToMainActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}