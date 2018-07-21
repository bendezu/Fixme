package com.yandex.dev.fixme.controller

/**
 * Created by Elbek D. on 21.07.2018.
 */
interface ViewController {
    fun updateScore(score: Int)
    fun updateLife(life: Int)
    fun exit()
}