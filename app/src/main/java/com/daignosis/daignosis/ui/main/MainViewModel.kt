package com.daignosis.daignosis.ui.main

import androidx.lifecycle.ViewModel
import com.daignosis.daignosis.data.repository.DaignosisRepository

class MainViewModel (private val repository: DaignosisRepository): ViewModel() {
    fun getToken() = repository.getToken()
    fun rmvSession() = repository.rmvSession()

    fun getArticle() = repository.getArticleLimit()
}