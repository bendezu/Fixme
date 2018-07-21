package com.yandex.dev.fixme

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast

class StartMenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_menu)
    }

    fun play(view: View) {
        Toast.makeText(this, "PLAY", Toast.LENGTH_SHORT).show()
    }
}
