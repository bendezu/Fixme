package com.yandex.dev.fixme

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View

class StartMenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_menu)
    }

    fun play(view: View) {
        intent  = Intent(this, GameFieldActivity::class.java)
        startActivity(intent)
    }
}
