package com.forgettingwords.jeff.forgettingwords

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.forgettingwords.jeff.forgettingwords.R
import android.content.Intent
import java.util.*


class WelcomeActivity : AppCompatActivity() {
    val DELAY: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        val task = object : TimerTask() {
            override fun run() {
                val mainIntent = Intent().setClass(
                        applicationContext, MainActivity::class.java)
                finish()
                startActivity(mainIntent)
            }
        }
        val timer = Timer()
        timer.schedule(task, DELAY)
    }
}