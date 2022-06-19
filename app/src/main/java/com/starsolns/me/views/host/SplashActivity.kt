package com.starsolns.me.views.host

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.starsolns.me.R
import com.starsolns.me.util.Settings

class SplashActivity : AppCompatActivity() {

    private lateinit var settings: Settings

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
                       startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }, 3000)
        }

}