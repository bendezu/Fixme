package com.yandex.dev.fixme.controller

import android.view.View
import com.yandex.dev.fixme.base.BaseItem
import java.util.*

class Controller(val items: List<BaseItem>) {

    companion object {
        const val BUGS_EXPECT = 80
        const val KOSTIL_EXPECT = 20


        const val LIVE_TIME = 5_000L

        const val STEP_TIME = 5_000L
        const val STEP_FREEZE = 1000

        const val MAX_LIVES = 5
    }
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

    private fun makeStep() {
        Thread.sleep(random.nextInt(STEP_FREEZE).toLong())

        val item = items.getInvisibleItem() ?: return
        val type = getType()


        item.changeImage(type)
        item.appear()
        item.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (item.isVisible()) {
                    ++score
                    item.disappear()
                }
            }
        })

        timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        if (item.isVisible()) {
                            --lives
                            item.destroy()
                        }
                    }
                },
                LIVE_TIME
        )
    }

    fun startGame() {
        timer.schedule(object : TimerTask() {
            override fun run() {
                makeStep()
            }
        },0L, STEP_TIME)
    }
}