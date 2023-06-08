package com.daignosis.daignosis.ui.profile

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class ProfileViewModel(private val repository: DaignosisRepository): ViewModel() {
    fun getToken() = repository.getToken()
    fun logout() = repository.logout()
    fun getUserProfile(token: String) = repository.getUserProfile(token)
}