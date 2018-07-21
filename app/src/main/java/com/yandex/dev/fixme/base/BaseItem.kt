package com.yandex.dev.fixme.base

import android.util.Log
import android.view.View
import android.widget.ImageView
import com.yandex.dev.fixme.R

/**
 * Created by Elbek D. on 21.07.2018.
 */
open class BaseItem(val view: ImageView) {
    enum class TYPES {
        BUG, KOSTIL
    }

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

    fun changeImage(type: TYPES) {
        when (type) {
            TYPES.BUG -> view.setImageResource(R.drawable.ic_launcher_background)
            TYPES.KOSTIL -> view.setImageResource(R.drawable.ic_launcher_background)
        }
    }
}