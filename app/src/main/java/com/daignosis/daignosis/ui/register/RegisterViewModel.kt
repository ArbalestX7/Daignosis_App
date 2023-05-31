package com.daignosis.daignosis.ui.register

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class RegisterViewModel (private val repository: DaignosisRepository): ViewModel() {

    fun register(
        username: String, email:String, password:String
    ) = repository.register(username, email, password)
}