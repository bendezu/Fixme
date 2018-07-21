package com.yandex.dev.fixme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.yandex.dev.fixme.base.BaseItem
import com.yandex.dev.fixme.controller.Controller
import kotlinx.android.synthetic.main.activity_game_field.*

class GameFieldActivity : AppCompatActivity() {

    private lateinit var controller: Controller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_field)

        val items: List<BaseItem> = listOf<ImageView>(
                item1,
                item2,
                item3,
                item4,
                item5,
                item6,
                item7,
                item8,
                item9
        ).map { BaseItem(it) }
        controller = Controller(items)
        controller.startGame()
    }
}
