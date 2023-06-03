package com.daignosis.daignosis.utils

import android.view.View
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Util {

    const val base_Url = "https://develop-dot-lukaku-386014.df.r.appspot.com/"

    fun String.withDateFormat(): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale("id","ID"))
        val date = format.parse(this) as Date
        return DateFormat.getDateInstance(DateFormat.FULL, Locale.getDefault()).format(date)
    }

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}