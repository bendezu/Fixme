package com.yandex.dev.fixme.controller

import android.view.View
import com.yandex.dev.fixme.base.BaseItem
import java.util.*

class Controller(val items: List<BaseItem>, val viewController: ViewController) {

    companion object {
        const val BUGS_EXPECT = 70
        const val KOSTIL_EXPECT = 30

        const val STEP_TIME = 500L
        const val MAX_LIVES = 3
    }
    var STEP_FREEZE = 1_500
    var LIVE_TIME = 5_000L



    private val random = Random()
    private val timer = Timer()

    private var score = 0
    private var lives = MAX_LIVES


    private fun List<BaseItem>.getInvisibleItem(): BaseItem? {
        val list = this.filter { it.view.visibility == View.INVISIBLE }
        if (list.isEmpty()) return null
        return list.get(random.nextInt(list.size))
    }
    private fun BaseItem.isVisible(): Boolean {
        return this.view.visibility == View.VISIBLE
    }
    private fun getType(): BaseItem.TYPES {
        val expect = random.nextInt(BUGS_EXPECT + KOSTIL_EXPECT)
        return when {
            expect > BUGS_EXPECT -> BaseItem.TYPES.KOSTIL
            expect < BUGS_EXPECT -> BaseItem.TYPES.BUG
            else -> BaseItem.TYPES.BUG
        }
    }

    private fun getTask(type: BaseItem.TYPES, item: BaseItem): TimerTask {
        return when(type) {
            BaseItem.TYPES.BUG -> object : TimerTask() {
                override fun run() {
                    if (item.isVisible()) {
                        viewController.updateLife(--lives)
                        if (lives == 0) {
                            viewController.exit()
                        }
                        item.disappear()
                        item.setOnClickListener(null)
                    }
                }
            }
            BaseItem.TYPES.KOSTIL -> object : TimerTask() {
                override fun run() {
                    if (item.isVisible()) {
                        item.disappear()
                        item.setOnClickListener(null)
                    }
                }
            }
        }
    }
    private fun getListener(type: BaseItem.TYPES, item: BaseItem, timerTask: TimerTask): View.OnClickListener {
        return when(type) {
            BaseItem.TYPES.KOSTIL -> View.OnClickListener {
                if (item.isVisible()) {
                    viewController.updateLife(--lives)
                    if (lives == 0) {
                        viewController.exit()
                    }
                    timerTask.cancel()
                    item.destroy()
                }
            }
            BaseItem.TYPES.BUG -> View.OnClickListener {
                if (item.isVisible()) {
                    score += 10
                    viewController.updateScore(score)
                    timerTask.cancel()
                    item.destroy()
                }
            }
        }
    }

    private fun makeStep() {
        if (STEP_FREEZE > 0) --STEP_FREEZE
        if (LIVE_TIME > 500L) LIVE_TIME -= 10

        Thread.sleep(random.nextInt(STEP_FREEZE).toLong())

        val item = items.getInvisibleItem() ?: return
        val type = getType()

        item.changeImage(type)

        val timerTask: TimerTask
        val listener: View.OnClickListener

        timerTask = getTask(type, item)
        listener = getListener(type, item, timerTask)

        item.setOnClickListener(listener)
        timer.schedule(timerTask, LIVE_TIME)
        item.appear()
    }

    fun startGame() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                makeStep()
            }
        },0L, STEP_TIME)
    }

    fun stopGame() {
        timer.cancel()
    }
}