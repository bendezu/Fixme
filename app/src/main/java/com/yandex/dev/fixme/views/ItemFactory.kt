package com.yandex.dev.fixme.views

import android.widget.ImageView
import com.yandex.dev.fixme.base.BaseItem

/**
 * Created by Elbek D. on 21.07.2018.
 */
class ItemFactory {
    companion object {
        fun createBug(view: ImageView): BaseItem {

            return BugItem(view)
        }

        fun createKosil(view: ImageView): BaseItem {
            return KostilItem(view)
        }
    }
}