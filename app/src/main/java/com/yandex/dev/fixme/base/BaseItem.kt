package com.yandex.dev.fixme.base

import android.view.View

/**
 * Created by Elbek D. on 21.07.2018.
 */
abstract class BaseItem {
    private lateinit var onDisappearListener: () -> Unit
    private lateinit var view: View
    lateinit var position: Pair<Int, Int>

    fun setOnClickListener(listener: View.OnClickListener) {
        view.setOnClickListener(listener)
    }

    fun setOnDisappearListener(listener: () -> Unit) {
        onDisappearListener = listener
    }

    abstract fun appear()
    abstract fun disappear()
    abstract fun onDestroyed()
}