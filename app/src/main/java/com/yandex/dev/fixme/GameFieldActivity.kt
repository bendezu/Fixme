package com.yandex.dev.fixme

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ImageView
import com.yandex.dev.fixme.base.BaseItem
import com.yandex.dev.fixme.controller.Controller
import com.yandex.dev.fixme.controller.ViewController
import kotlinx.android.synthetic.main.activity_game_field.*

class GameFieldActivity : AppCompatActivity(), ViewController {
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

    override fun updateScore(score: Int) {
        bugFixed.text = String.format(getString(R.string.bugFixed), score)
    }

    override fun updateLife(life: Int) {
        hearts.getChildAt(life - 1).visibility = View.INVISIBLE
    }

    override fun exit() {
        startActivity(Intent(this, StartMenuActivity::class.java))
        finish()
    }
}
