package com.yandex.dev.fixme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yandex.dev.fixme.controller.Controller

class GameFieldActivity : AppCompatActivity() {

    private val controller = Controller()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        controller.startGame()
    }
}
