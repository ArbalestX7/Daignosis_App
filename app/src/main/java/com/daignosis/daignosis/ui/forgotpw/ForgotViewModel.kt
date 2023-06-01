package com.daignosis.daignosis.ui.forgotpw

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class ForgotViewModel(private val repository: DaignosisRepository): ViewModel() {

    fun forgotPass(username:String) = repository.forgotPw(username)
}