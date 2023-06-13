package com.daignosis.daignosis.ui.consultation

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class HistoryViewModel (private val repository: DaignosisRepository): ViewModel() {

    fun getToken() = repository.getToken()
    fun newSession(token:String) = repository.newSession(token)

    fun history(token: String) = repository.historySession(token)
}