package com.daignosis.daignosis.utils

import android.util.Patterns
import android.view.View
import com.daignosis.daignosis.utils.Util.withDateFormat
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
    fun String.withFormatDateMsg(): String {
        val format = SimpleDateFormat("dd/MM/yyyy", Locale("id", "ID"))
        val date = format.parse(this) as Date
        return DateFormat.getDateInstance(DateFormat.DATE_FIELD, Locale.getDefault()).format(date)
    }

    fun String.isValidEmail() = Patterns.EMAIL_ADDRESS.matcher(this).matches()

    fun View.visible() {
        this.visibility = View.VISIBLE
    }

    fun View.gone() {
        this.visibility = View.GONE
    }
}