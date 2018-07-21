package com.yandex.dev.fixme.base

import android.view.View

/**
 * Created by Elbek D. on 21.07.2018.
 */
abstract class BaseItem(val view: View) {
    lateinit var position: Pair<Int, Int>
    abstract fun appear()
    abstract fun disappear()
    abstract fun onDestroyed()
}