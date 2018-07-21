package com.yandex.dev.fixme.views

import android.util.Log
import android.view.View
import com.yandex.dev.fixme.base.BaseItem

/**
 * Created by Elbek D. on 21.07.2018.
 */
class DebugView(view: View) : BaseItem(view) {
    companion object {
        const val TAG = "DebugView"
    }

    override fun appear() {
        view.visibility = View.VISIBLE
        Log.d(TAG, "view appeared")
    }

    override fun disappear() {
        view.visibility = View.GONE
        Log.d(TAG, "view is gone itself")
    }

    override fun onDestroyed() {
        view.visibility = View.GONE
        Log.d(TAG, "view tapped and destroyed")
    }
}