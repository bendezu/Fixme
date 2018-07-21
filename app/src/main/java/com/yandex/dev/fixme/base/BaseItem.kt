package com.yandex.dev.fixme.base

import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.yandex.dev.fixme.R

/**
 * Created by Elbek D. on 21.07.2018.
 */
open class BaseItem(val view: ImageView) {

    private val handler = Handler(view.context.mainLooper)
    var type: TYPES = TYPES.BUG

    enum class TYPES {
        BUG, KOSTIL
    }

    companion object {
        const val TAG = "BaseItem"
    }

    var action: () -> Unit = {}

    lateinit var position: Pair<Int, Int>

    open fun appear() {
        handler.post {
            view.visibility = View.VISIBLE
        }
        Log.d(TAG, "item appeared")
    }

    open fun disappear() {
        handler.post {
            view.visibility = View.INVISIBLE
        }
        Log.d(TAG, "item disappeared itself")
    }

    open fun destroy() {
        handler.post {
            view.visibility = View.INVISIBLE
        }
        Log.d(TAG, "item tapped by user")
    }

    fun setOnClickListener(listener: View.OnClickListener) {
        handler.post { view.setOnClickListener(listener) }
    }

    fun changeImage(type: TYPES) {
        this.type = type
        handler.post {
            when (type) {
                TYPES.BUG -> view.background = view.context.getDrawable(R.drawable.greenbug)
                TYPES.KOSTIL -> view.background = view.context.getDrawable(R.drawable.redbug)
            }
        }
    }
}