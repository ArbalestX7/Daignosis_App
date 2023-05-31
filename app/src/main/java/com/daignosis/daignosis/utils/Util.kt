package com.daignosis.daignosis.utils

import android.view.View

object Util {

    const val base_Url = "https://develop-dot-lukaku-386014.df.r.appspot.com/"

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}