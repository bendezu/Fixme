package com.yandex.dev.fixme.controller

import android.view.View
import com.yandex.dev.fixme.base.BaseItem
import java.util.*

class Controller {

    companion object {
        const val SIZE = 3
        const val LIVE_TIME = 1_000L
        const val STEP_TIME = 1_000
        const val MAX_LIVES = 5
    }
    private val random = Random()
    private val timer = Timer()

    private var score = 0
    private var lives = MAX_LIVES

    private val map: Array<Array<Boolean>> = Array(SIZE, { Array(SIZE, {false}) })
    private val items: List<BaseItem> = ArrayList()

    private fun Array<Array<Boolean>>.getEmptyCell(): Pair<Int, Int> {
        var x: Int
        var y: Int
        do {
            x = random.nextInt(SIZE)
            y = random.nextInt(SIZE)

        } while (map[x][y])

        return x to y
    }
    private fun Array<Array<Boolean>>.setItem(cell: Pair<Int, Int>) {
        map[cell.first][cell.second] = true
    }
    private fun Array<Array<Boolean>>.removeItem(cell: Pair<Int, Int>) {
        map[cell.first][cell.second] = false
    }
    private fun List<BaseItem>.getFreeItem(): BaseItem {
        var position: Int
        do {
            position = Random().nextInt(this.size)
        } while (items[position].isVisible())

        return items[position]
    }
    private fun BaseItem.isVisible(): Boolean {
        return this.view.visibility == View.VISIBLE
    }

    private fun makeStep() {
        val position = map.getEmptyCell()
        val item = items.getFreeItem()

        map.setItem(position)
        item.position = position
        item.appear()
        item.view.setOnClickListener {
            if (item.isVisible()) {
                ++score
                item.disappear()
                map.removeItem(item.position)
            }
        }

        timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        if (item.isVisible()) {
                            --lives
                            item.onDestroyed()
                            map.removeItem(item.position)
                        }
                    }
                },
                LIVE_TIME
        )
    }

    fun startGame() {
        timer.schedule(Step(),0L)
    }

    inner class Step() : TimerTask() {

        override fun run() {
            this@Controller.makeStep()
            this@Controller.timer.schedule(
                    this@Step,
                    this@Controller.random.nextInt(STEP_TIME).toLong()
            )
        }
    }

}