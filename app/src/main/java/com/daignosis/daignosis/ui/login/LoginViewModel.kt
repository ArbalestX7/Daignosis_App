package com.daignosis.daignosis.ui.login

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.model.LoginModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class LoginViewModel(private val repository: DaignosisRepository): ViewModel() {
    fun login(email: String, password:String) = repository.login(email, password)
}