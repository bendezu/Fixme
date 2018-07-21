package com.yandex.dev.fixme.views

import android.widget.ImageView
import com.yandex.dev.fixme.R
import com.yandex.dev.fixme.base.BaseItem

/**
 * Created by Elbek D. on 21.07.2018.
 */
class KostilItem(view: ImageView) : BaseItem(view) {
    init {
        view.setImageResource(R.mipmap.ic_launcher)
    }
}