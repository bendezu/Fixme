package com.yandex.dev.fixme.base

import android.util.Log
import android.view.View
import android.widget.ImageView

/**
 * Created by Elbek D. on 21.07.2018.
 */
abstract class BaseItem(val view: ImageView) {
    companion object {
        const val TAG = "BaseItem"
    }

    var action: () -> Unit = {}

    lateinit var position: Pair<Int, Int>

    open fun appear() {
        view.visibility = View.VISIBLE
        Log.d(TAG, "item appeared")
    }

    open fun disappear() {
        view.visibility = View.GONE
        Log.d(TAG, "item disappeared itself")
    }

    open fun destroy() {
        view.visibility = View.GONE
        Log.d(TAG, "item tapped by user")
    }
}