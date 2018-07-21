package com.yandex.dev.fixme.base

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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

    open fun appear() {
        handler.post {
            view.alpha = 0f
            view.translationY = 0f
            view.scaleX = 1f
            view.scaleY = 1f
            view.animate().setListener(null)
            view.visibility = View.VISIBLE
            view.animate().translationY(-view.height.toFloat()/4).alpha(1f)
        }
        Log.d(TAG, "item appeared")
    }

    open fun disappear() {
        handler.post {

            view.animate().translationY(0f).alpha(0f).
                    setListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    view.visibility = View.INVISIBLE

                }
            })
        }
        Log.d(TAG, "item disappeared itself")
    }

    open fun destroy() {
        handler.post {
            view.animate().scaleX(2f).scaleY(2f).alpha(0f).
                    setListener(object: AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            view.visibility = View.INVISIBLE
                            view.scaleX = 1f
                            view.scaleY = 1f
                            view.translationY = 0f
                        }
                    })
        }
        Log.d(TAG, "item tapped by user")
    }

    fun setOnClickListener(listener: View.OnClickListener?) {
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